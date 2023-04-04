package completableFutureExample;

import completableFutureExample.database.EmployeeDatabase;
import completableFutureExample.dto.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executed By"+Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        },executor);
        return supplyAsync.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
        employees.stream().forEach(System.out::println);
    }
}
