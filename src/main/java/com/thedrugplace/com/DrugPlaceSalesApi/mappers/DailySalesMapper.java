package com.thedrugplace.com.DrugPlaceSalesApi.mappers;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailySales;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.sales.DailySalesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.BranchRepository;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.StaffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * Mapper class for converting between DailySales entities and DailySalesDto data transfer objects.
 */
@Component
public class DailySalesMapper {
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StaffRepository staffRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a DailySales entity to a DailySalesDto.
     *
     * @param dailySales The DailySales entity to be converted.
     * @return The corresponding DailySalesDto.
     */
    public DailySalesDto entityToDto(DailySales dailySales) {
        return modelMapper.map(dailySales, DailySalesDto.class);
    }

    /**
     * Converts a DailySalesDto to a DailySales entity.
     *
     * @param dailySalesDto The DailySalesDto to be converted.
     * @return The corresponding DailySales entity.
     */
    public DailySales dtoToEntity(DailySalesDto dailySalesDto) throws ParseException {
        DailySales dailySales = new DailySales();
        dailySales.setSaleAmount(dailySales.getSaleAmount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dailySales.setSaleAmount(dailySalesDto.getSaleAmount());
        dailySales.setSaleDate(dateFormat.parse(dailySalesDto.getSaleDate()));
        dailySales.setBranch(getBranchByCode(dailySalesDto.getBranchCode()));
        dailySales.setStaff(getStaffBYPhone(dailySalesDto.getStaffPhone()));
        dailySales.setPaymentMethod(dailySalesDto.getPaymentMethod());
        dailySales.setTransactionReference(dailySalesDto.getTransactionReference());
        dailySales.setCreatedAt(LocalDateTime.now());
        return dailySales;
    }

    private Branch getBranchByCode(String branchCode) {

        Branch branch = branchRepository.findByBranchCode(branchCode);
        if (branch == null) {
            throw new CustomException("Branch not found");
        }
        return branch;

    }

    private Staff getStaffBYPhone(String staffPhone) {

        Staff staff = staffRepository.findByStaffPhone(staffPhone);
        if (staff == null) {
            throw new CustomException("staff not found");
        }
        return staff;

    }
}
