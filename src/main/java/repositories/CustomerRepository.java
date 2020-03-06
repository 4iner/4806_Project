package repositories;

import models.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{
    List<Customer> findByName(String name);
    List<Customer> findByAddress(String address);
    List<Customer> findByEmail(String email);
    List<Customer> findByNumber(String Number);
}