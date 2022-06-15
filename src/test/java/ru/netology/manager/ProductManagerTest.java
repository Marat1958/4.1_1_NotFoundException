package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product book1 = new Book(1, "Изучаем Java", 1000, "Кэти Сьерра и Берт Бейтс");
    private Product book2 = new Book(2, "Алхимик", 1500, "Майкл Скотт");
    private Product book3 = new Book(3, "Алхимик", 1600, "Питер Джеймс");
    private Product book4 = new Book(4, "Фантастика", 2000, "Сидоров");
    private Product phone1 = new Smartphone(11, "A110", 55000, "Samsung");
    private Product phone2 = new Smartphone(22, "iPhone 11", 107000, "iPhone");

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(phone1);
        manager.add(phone2);
    }

    @Test
    void shouldGetAll() {
        Product[] expected = new Product[]{book1, book2, book3, book4, phone1, phone2};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByTitle() {
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy("Изучаем Java");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldToFindSomeBooksByTitle() {
        Product[] expected = new Product[]{book2, book3};
        Product[] actual = manager.searchBy("Алхимик");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByTitle() {
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy("iPhone 11");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFind() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Поэзия");
        assertArrayEquals(expected, actual);
    }
}
