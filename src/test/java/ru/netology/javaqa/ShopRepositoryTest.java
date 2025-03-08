package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    ShopRepository repository = new ShopRepository();
    Product product1 = new Product(12, "Молоко", 75);
    Product product2 = new Product(4, "Хлеб", 40);
    Product product3 = new Product(18, "Печенье", 120);

    @Test
    public void AddNewRepo() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AddNewRepoRepeat() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        Product product100 = new Product(12, "Картофель", 45);
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product100);
        });
    }

    @Test
    public void RemoveByIdNotFound() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Product product100 = new Product(55, "Картофель", 45);
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(55);
        });
    }

    @Test
    public void FindById() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Product expected = product1;
        Product actual = repository.findById(12);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void RemoveByIdSucess() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.removeById(12);

        Product[] excepted = {product2, product3};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(excepted, actual);
    }
}
