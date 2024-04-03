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
        DailySalesDto dailySalesDto = new DailySalesDto();
        dailySalesDto.setSaleAmount(dailySales.getSale_amount());
        dailySalesDto.setSaleDate(dailySales.getSale_date().toString());
        dailySalesDto.setBranchCode(dailySales.getBranch().getBranch_code());
        dailySalesDto.setStaffPhone(dailySales.getStaff().getStaff_phone());
        dailySalesDto.setPaymentMethod(dailySales.getPayment_method());
        dailySalesDto.setTransactionReference(dailySales.getTransaction_reference());
        return dailySalesDto;
    }

    /**
     * Converts a DailySalesDto to a DailySales entity.
     *
     * @param dailySalesDto The DailySalesDto to be converted.
     * @return The corresponding DailySales entity.
     */
    public DailySales dtoToEntity(DailySalesDto dailySalesDto) throws ParseException {
        DailySales dailySales = new DailySales();
        dailySales.setSale_amount(dailySales.getSale_amount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dailySales.setSale_amount(dailySalesDto.getSaleAmount());
        dailySales.setSale_date(dateFormat.parse(dailySalesDto.getSaleDate()));
        dailySales.setBranch(getBranchByCode(dailySalesDto.getBranchCode()));
        dailySales.setStaff(getStaffBYPhone(dailySalesDto.getStaffPhone()));
        dailySales.setPayment_method(dailySalesDto.getPaymentMethod());
        dailySales.setTransaction_reference(dailySalesDto.getTransactionReference());
        dailySales.setCreated_at(LocalDateTime.now());
        return dailySales;
    }

    private Branch getBranchByCode(String branchCode) {

        Branch branch = branchRepository.findBranchByBranch_code(branchCode);
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
