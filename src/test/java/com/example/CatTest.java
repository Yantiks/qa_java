package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    private Feline felineMock;

    private Cat cat;

    @Test
    public void getSoundTest() {
        Feline feline = new Feline();
        cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void getFoodVerifyTest() throws Exception {
        cat = new Cat(felineMock);
        cat.getFood();
        Mockito.verify(felineMock, Mockito.times(1)).eatMeat();
    }

    @Test
    public void getFoodAssertTest() throws Exception {
        Feline feline = new Feline();
        cat = new Cat(feline);
        assertEquals(List.of("Животные", "Птицы", "Рыба"), cat.getFood());
    }
}
