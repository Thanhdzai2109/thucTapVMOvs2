package com.example.jwt.demo.service.impl;

import com.example.jwt.demo.dto.TinhTrangSucKhoe;
import com.example.jwt.demo.dto.XacSuat;
import com.example.jwt.demo.model.HeightWeight;
import com.example.jwt.demo.repository.ChieuCaoCanNangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuzzyLogic {

    @Autowired
    private ChieuCaoCanNangRepository chieuCaoCanNangRepository;

    // tìm chiều cao cân nặng theo giới tính và tháng tuổi
    public HeightWeight timChieuCaoCanNang(String gioiTinh, double thang) {
        List<HeightWeight> datas = chieuCaoCanNangRepository.findAll();
        for (HeightWeight heightWeight : datas) {
            if (gioiTinh.equals(heightWeight.getGender()) && thang == heightWeight.getAge()) {
                return heightWeight;
            }
        }

        // trả về khi không có trong db
        return tinhToanChieuCaoCanNang(gioiTinh, thang);
    }

    public HeightWeight tinhToanChieuCaoCanNang(String gioiTinh, double thang) {
        List<HeightWeight> datas = chieuCaoCanNangRepository.findAll();
        datas =datas.stream().sorted(Comparator.comparing(HeightWeight::getAge)).collect(Collectors.toList());
        HeightWeight smin = null, smax = null;
        for (int i = 0; i < datas.size() - 2; i++) {
            HeightWeight heightWeight = datas.get(i);
            HeightWeight heightWeight1 = datas.get(i + 2);
            if (gioiTinh.equals(heightWeight.getGender()) && thang > heightWeight.getAge() && thang <= heightWeight1.getAge()) {
                smin = heightWeight;
                smax = heightWeight1;
                break;
            }
        }
        double chieuCaoToiThieu = smin.getMinHeight() + (smax.getMinHeight() - smin.getMinHeight()) * (thang - smin.getAge()) / (smax.getAge() - smin.getAge());
        double chieuCaoToiDa = smin.getMaxHeight() + (smax.getMaxHeight() - smin.getMaxHeight()) * (thang - smin.getAge()) / (smax.getAge() - smin.getAge());
        double canNangToiThieu = smin.getMinWeight() + (smax.getMinWeight() - smin.getMinWeight()) * (thang - smin.getAge()) / (smax.getAge() - smin.getAge());


        double canNangToiDa = smin.getMaxWeight() + (smax.getMaxWeight() - smin.getMaxWeight()) * (thang - smin.getAge()) / (smax.getAge() - smin.getAge());
        HeightWeight heightWeight = new HeightWeight();
        heightWeight.setGender(gioiTinh);
        heightWeight.setAge(thang);
        heightWeight.setMinHeight(chieuCaoToiThieu);
        heightWeight.setMaxHeight(chieuCaoToiDa);
        heightWeight.setMinWeight(canNangToiThieu);
        heightWeight.setMaxWeight(canNangToiDa);
        return heightWeight;
    }

    public void tinhXacSuat(XacSuat cHeight, XacSuat cWeight, String sex, double month, double weight, double height) {
        HeightWeight heightWeight = timChieuCaoCanNang(sex, month);

        double heightMin = heightWeight.getMinHeight();
        double heightMax = heightWeight.getMaxHeight();
        double heightTb = (heightMax + heightMin) / 2;

        if (height < heightMin)
            cHeight.setThap(1);
        else if (height >= heightMin
                && height < heightTb) {
            cHeight.setBinhThuong(2 * (height - heightMin) / (heightMax - heightMin));
            cHeight.setThap(1 - cHeight.getBinhThuong());
        } else if (height >= heightTb
                && height <= heightMax) {
            cHeight.setCao(2 * (heightMax - height) / (heightMax - heightMin));
            cHeight.setBinhThuong(1 - cHeight.getCao());
        } else if (height >= heightMax) {
            cHeight.setCao(1);
        }

        double weightMin = heightWeight.getMinWeight();
        double weightMax = heightWeight.getMaxWeight();
        double weightTb = (weightMax + weightMin) / 2;
        if (weight < weightMin) {
            cWeight.setThap(1);
        } else if (weight >= weightMin && weight < weightTb) {
            cWeight.setBinhThuong(2 * (double) (weight - weightMin) / (weightMax - weightMin));
            cWeight.setThap(1 - cWeight.getBinhThuong());
        } else if (weight >= weightTb && weight <= weightMax) {
            cWeight.setCao(2 * (weightMax - weight) / (weightMax - weightMin));
            cWeight.setBinhThuong(1 - cWeight.getCao());
        } else if (weight >= weightMax) {
            cWeight.setCao(1);
        }
    }

    public String xacDinhTheTrang(String sex, double month, double weight, double height) {
        XacSuat cHeight = new XacSuat();
        XacSuat cWeight = new XacSuat();


        tinhXacSuat(cHeight, cWeight, sex, month, weight, height);


        TinhTrangSucKhoe tinhTrangSucKhoe = new TinhTrangSucKhoe();
        tinhTrangSucKhoe.setGayCap2(Double.min(cHeight.getThap(), cWeight.getThap()));
        tinhTrangSucKhoe.setGayCap1(Double.max(Double.min(cHeight.getCao(), cWeight.getThap()), Double.max(Double.min(cHeight.getBinhThuong(), cWeight.getThap()), Double.min(cHeight.getThap(), cWeight.getBinhThuong()))));
        tinhTrangSucKhoe.setBeoCap1(Double.max(Double.min(cHeight.getBinhThuong(), cWeight.getCao()), Double.min(cHeight.getCao(), cWeight.getCao())));
        tinhTrangSucKhoe.setBeoCap2(Double.min(cHeight.getThap(), cWeight.getCao()));
        tinhTrangSucKhoe.setBinhThuong(Double.max(Double.min(cHeight.getBinhThuong(), cWeight.getBinhThuong()), Double.min(cHeight.getCao(), cWeight.getBinhThuong())));
        double probabilityMax = 0;
        probabilityMax = Double.max(tinhTrangSucKhoe.getGayCap1(), Double.max(tinhTrangSucKhoe.getGayCap2(),
                Double.max(tinhTrangSucKhoe.getBinhThuong(), Double.max(tinhTrangSucKhoe.getBeoCap1(), tinhTrangSucKhoe.getBeoCap2()))));
        if (probabilityMax == tinhTrangSucKhoe.getGayCap1()) return "Suy dinh dưỡng cấp 1";
        else if (probabilityMax == tinhTrangSucKhoe.getGayCap2()) return "Suy dinh dưỡng cấp 2";
        else if (probabilityMax == tinhTrangSucKhoe.getBinhThuong()) return "Bình thường";
        else if (probabilityMax == tinhTrangSucKhoe.getBeoCap1()) return "Thừa cân";
        else
            return "Béo phì";

    }
}
