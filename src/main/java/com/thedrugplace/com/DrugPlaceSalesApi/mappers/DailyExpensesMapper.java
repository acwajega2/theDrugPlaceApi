package com.thedrugplace.com.DrugPlaceSalesApi.mappers;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailyExpenses;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.DailyExpensesDto;
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
 * Mapper class for converting between DailyExpenses entities and DailyExpensesDto data transfer objects.
 */
@Component
public class DailyExpensesMapper {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StaffRepository staffRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a DailyExpenses entity to a DailyExpensesDto.
     *
     * @param dailyExpenses The DailyExpenses entity to be converted.
     * @return The corresponding DailyExpensesDto.
     */
    public DailyExpensesDto entityToDto(DailyExpenses dailyExpenses) {
        return modelMapper.map(dailyExpenses, DailyExpensesDto.class);
    }

    /**
     * Converts a DailyExpensesDto to a DailyExpenses entity.
     *
     * @param dailyExpensesDto The DailyExpensesDto to be converted.
     * @return The corresponding DailyExpenses entity.
     */
    public DailyExpenses dtoToEntity(DailyExpensesDto dailyExpensesDto) throws ParseException {
        DailyExpenses dailyExpenses = new DailyExpenses();
        dailyExpenses.setExpense_amount(dailyExpensesDto.getExpenseAmount());
        dailyExpenses.setExpense_category(dailyExpensesDto.getExpenseCategory());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dailyExpenses.setExpense_date(dateFormat.parse(dailyExpensesDto.getExpenseDate()));
        dailyExpenses.setReceipt_image_url(dailyExpensesDto.getReceiptImageUrl());
        dailyExpenses.setBranch(getBranchByCode(dailyExpensesDto.getBranchCode()));
        dailyExpenses.setStaff(getStaffBYPhone(dailyExpensesDto.getStaffPhone()));
        dailyExpenses.setTransaction_reference(dailyExpensesDto.getTransactionReference());
        dailyExpenses.setCreated_at(LocalDateTime.now());
        return dailyExpenses;
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
