package com.thedrugplace.com.DrugPlaceSalesApi.services;

import com.thedrugplace.com.DrugPlaceSalesApi.daos.Branch;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.DailyExpenses;
import com.thedrugplace.com.DrugPlaceSalesApi.daos.Staff;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.BranchExpensesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.BranchExpensesPerformanceDto;
import com.thedrugplace.com.DrugPlaceSalesApi.dtos.expenses.DailyExpensesDto;
import com.thedrugplace.com.DrugPlaceSalesApi.exceptions.CustomException;
import com.thedrugplace.com.DrugPlaceSalesApi.interfaces.DailyExpensesService;
import com.thedrugplace.com.DrugPlaceSalesApi.mappers.DailyExpensesMapper;
import com.thedrugplace.com.DrugPlaceSalesApi.repos.DailyExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DailyExpensesServiceImpl implements DailyExpensesService {

    @Autowired
    private DailyExpensesRepository dailyExpensesRepository;

    @Autowired
    private DailyExpensesMapper dailyExpensesMapper; // Inject the DailyExpensesMapper

    @Override
    public DailyExpensesDto createDailyExpenses(DailyExpensesDto dailyExpensesDto) {
        try {
            var dailyExpenses = dailyExpensesMapper.dtoToEntity(dailyExpensesDto);
            if (dailyExpensesRepository.findByTransactionReference(dailyExpensesDto.getTransactionReference()) != null) {
                throw new CustomException("Expense already recorded");
            }
            return dailyExpensesMapper.entityToDto(dailyExpensesRepository.save(dailyExpenses));
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to create daily expenses: " + ex.getMessage());
        }
    }

    @Override
    public DailyExpensesDto getDailyExpensesById(Long expenseId) {
        try {
            Optional<DailyExpenses> expensesOptional = dailyExpensesRepository.findById(expenseId);
            return expensesOptional.map(dailyExpensesMapper::entityToDto).orElse(null);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get daily expenses by ID: " + ex.getMessage());
        }
    }

    @Override
    public List<DailyExpensesDto> getAllDailyExpenses() {
        try {
            List<DailyExpenses> dailyExpensesList = dailyExpensesRepository.findAll();
            return dailyExpensesList.stream().map(dailyExpensesMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get all daily expenses: " + ex.getMessage());
        }
    }

    @Override
    public DailyExpensesDto updateDailyExpenses(Long expenseId, DailyExpensesDto dailyExpensesDto) {
        try {
            if (dailyExpensesRepository.existsById(expenseId)) {
                var dailyExpenses = dailyExpensesMapper.dtoToEntity(dailyExpensesDto);
                return dailyExpensesMapper.entityToDto(dailyExpensesRepository.save(dailyExpenses));
            }
            return null;
        } catch (DataAccessException | ParseException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to update daily expenses: " + ex.getMessage());
        }
    }

    @Override
    public void deleteDailyExpenses(Long expenseId) {
        try {
            dailyExpensesRepository.deleteById(expenseId);
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to delete daily expenses: " + ex.getMessage());
        }
    }

    @Override
    public List<DailyExpensesDto> getBranchExepnsesByBranchCode(String branchCode) {
        try {
            List<DailyExpenses> dailyExpensesList = dailyExpensesRepository.findExpensesByBranchCode(branchCode);
            return dailyExpensesList.stream().map(dailyExpensesMapper::entityToDto).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get  daily expenses: " + ex.getMessage());
        }
    }


    @Override
    public List<BranchExpensesDto> getBranchExpensesWithStaffDetailsOrderByDateDesc() {
        try {
            List<Object[]> results = dailyExpensesRepository.findBranchExpensesWithStaffDetailsOrderByDateDesc();
            return results.stream()
                    .map(result -> {
                        Branch branch = (Branch) result[0];
                        Staff staff = (Staff) result[1];
                        Date date = (Date) result[2];
                        double totalExpenses = (double) result[3];
                        return new BranchExpensesDto(branch, staff, date, totalExpenses);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get BranchExpenses with Staff Details ordered by date: " + ex.getMessage());
        }
    }

    @Override
    public List<BranchExpensesPerformanceDto> getBranchExpensesPerformanceByMonthAndYear() {
        try {
            List<Object[]> results = dailyExpensesRepository.findBranchExpensesPerformanceByMonthAndYear();
            return results.stream()
                    .map(result -> {
                        Branch branch = (Branch) result[0];
                        int year = (int) result[1];
                        int month = (int) result[2];
                        double totalExpenses = (double) result[3];
                        double averageExpenses = (double) result[4];
                        return new BranchExpensesPerformanceDto(branch, year, month, totalExpenses, averageExpenses);
                    })
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            // Handle the exception or rethrow a custom exception
            throw new CustomException("Failed to get getBranchExpensesPerformanceByMonthAndYear: " + ex.getMessage());
        }
    }
}