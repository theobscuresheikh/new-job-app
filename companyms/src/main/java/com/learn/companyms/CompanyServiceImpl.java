package com.learn.companyms;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            return false;
        }
        companyRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setName(company.getName());
                    existingCompany.setDescription(company.getDescription());
                    existingCompany.setAddress(company.getAddress());
                    existingCompany.setCity(company.getCity());
                    existingCompany.setCountry(company.getCountry());
                    existingCompany.setEmail(company.getEmail());
                    existingCompany.setPhoneNumber(company.getPhoneNumber());
                    companyRepository.save(existingCompany);
                    return true;
                })
                .orElse(false);
    }

}


