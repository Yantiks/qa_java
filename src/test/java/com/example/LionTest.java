package com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class LionTest {
    @RunWith(Parameterized.class)
    public static class ParametrizedLionTest {
        @Parameterized.Parameters(name = "sex = {0}, hasMane = {1}")
        public static Object[][] doesHaveMane() {
            return new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            };
        }

        private String sex;
        private boolean hasMane;
        private Lion lion;
        private Feline feline;

        public ParametrizedLionTest(String sex, boolean hasMane) {
            this.sex = sex;
            this.hasMane = hasMane;
        }

        @Test
        public void doesHaveManeTest() throws Exception {
            feline = new Feline();
            lion = new Lion(sex, feline);
            assertEquals(hasMane, lion.doesHaveMane());
        }
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class NotParametrizedLionTest {
        @Mock
        private Feline felineMock;
        private Lion lion;
        private Feline feline;

        @Test
        public void getKittensVerifyTest() throws Exception {
            lion = new Lion("Самец", felineMock);
            lion.getKittens();
            Mockito.verify(felineMock, Mockito.times(1)).getKittens();
        }

        @Test
        public void getKittensAssertTest() throws Exception {
            feline = new Feline();
            lion = new Lion("Самец", feline);
            assertEquals(1, lion.getKittens());
        }

        @Test
        public void getFoodVerifyTest() throws Exception {
            lion = new Lion("Самец", felineMock);
            lion.getFood();
            Mockito.verify(felineMock, Mockito.times(1)).getFood("Хищник");
        }

        @Test
        public void getFoodAssertTest() throws Exception {
            feline = new Feline();
            lion = new Lion("Самец", feline);
            assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());
        }

        @Test
        public void throwExceptionTest() throws Exception {
            Exception exception = Assert.assertThrows(
                    Exception.class, () -> {
                        lion = new Lion("Нечто", feline);
                    });
            assertEquals("Используйте допустимые значения пола животного - самец или самка", exception.getMessage());
        }
    }
}

