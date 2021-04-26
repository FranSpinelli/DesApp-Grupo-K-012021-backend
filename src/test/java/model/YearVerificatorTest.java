package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YearVerificatorTest {

    @Test
    void anYearVerificatorKnowIfAnYearIsAValidStartYearCASE1() {
        //CASE 1: it receives an year that it is smaller than 0
        YearVerificator verificator = new YearVerificator();

        assertFalse(verificator.isAValidStartYear(-0));
    }

    @Test
    void anYearVerificatorKnowIfAnYearIsAValidStartYearCASE2() {
        //CASE 2: it receives an year that it is in the future
        YearVerificator verificator = new YearVerificator();

        assertFalse(verificator.isAValidStartYear(2222));
    }

    @Test
    void anYearVerificatorKnowIfAnYearIsAValidStartYearCASE3() {
        //CASE 3: it receives an year greater than 0 and smaller than the current year
        YearVerificator verificator = new YearVerificator();

        assertTrue(verificator.isAValidStartYear(2020));
    }

    @Test
    void anYearVerificatorKnowIfAnYearIsAValidEndYearRegardaAStartYearCASE1() {
        //CASE 1: it receives an end year bigger to the start year received
        YearVerificator verificator = new YearVerificator();

        assertTrue(verificator.isAValidEndYearRegardToAStartYear(2000, 2020));
    }

    @Test
    void anYearVerificatorKnowIfAnYearIsAValidEndYearRegardaAStartYearCASE2() {
        //CASE 2: it receives an end year smaller to the start year received
        YearVerificator verificator = new YearVerificator();

        assertFalse(verificator.isAValidEndYearRegardToAStartYear(2000, 1998));
    }
}