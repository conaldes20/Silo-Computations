package com.conaldes.scomputations;

import android.content.Context;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.math3.util.Precision;

/**
 *
 * @author CONALDES
 */
public class SiloCom {
    //shell corn
    private double[][] expSCstaicpresures = {{0,  0.05,  0.1,  0.25,  0.5,  0.75,  1.0,  1.25,  1.5,  2.0},
            {2,  0.1,  0.1,  0.1,  0.1,  0.1,  0.1,  0.1,  0.1,  0.1},
            {4,  0.1,  0.1,  0.1,  0.1,  0.1,  0.1,  0.1,  0.2,  0.2},
            {6,  0.1,  0.1,  0.1,  0.1,  0.2,  0.3,  0.3,  0.4,  0.6},
            {8,  0.1,  0.1,  0.1,  0.2,  0.3,  0.5,  0.6,  0.8,  1.2},
            {10,  0.1,  0.1,  0.2,  0.3,  0.5,  0.8,  1.1,  1.4,  2.0},
            {12,  0.1,  0.1,  0.2,  0.5,  0.8,  1.2,  1.6,  2.1,  3.2},
            {14,  0.1,  0.1,  0.3,  0.7,  1.2,  1.7,  2.3,  3.0,  4.6},
            {16,  0.1,  0.1,  0.4,  0.9,  1.6,  2.4,  3.2,  4.2,  6.4},
            {18,  0.1,  0.2,  0.5,  1.2,  2.1,  3.1,  4.3,  5.6,  8.7},
            {20,  0.1,  0.2,  0.7,  1.6,  2.7,  4.0,  5.6,  7.3,  11.3},
            {25,  0.2,  0.4,  1.1,  2.6,  4.6,  7.0,  9.7,  12.8,  19.9},
            {30,  0.3,  0.5,  1.6,  4.1,  7.2,  11.0,  15.3,  20.3,  31.9},
            {40,  0.5,  1.0,  3.1,  8.1,  14.6,  22.6,  31.9,  42.5,  0},
            {50,  0.7,  1.6,  5.3,  14.0,  25.6,  39.9, 0,     0,    0}};

    //soybeans
    private double[][] expSBstaicpresures = {{0, 0.05, 0.1, 0.25, 0.5, 0.75, 1.0, 1.25, 1.5, 2.0},
            {2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1},
            {4, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2},
            {6, 0.1, 0.1, 0.1, 0.1, 0.2, 0.2, 0.3, 0.3, 0.5},
            {8, 0.1, 0.1, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.9},
            {10, 0.1, 0.1, 0.1, 0.3, 0.4, 0.6, 0.8, 1.0, 1.5},
            {12, 0.1, 0.1, 0.2, 0.4, 0.7, 0.9, 1.2, 1.6, 2.3},
            {14, 0.1, 0.1, 0.3, 0.6, 0.9, 1.3, 1.7, 2.2, 3.3},
            {16, 0.1, 0.1, 0.3, 0.8, 1.2, 1.8, 2.4, 3.0, 4.5},
            {18, 0.1, 0.2, 0.4, 1.0, 1.6, 2.3, 3.1, 4.0, 6.0},
            {20, 0.1, 0.2, 0.6, 1.2, 2.0, 3.0, 4.0, 5.1, 7.7},
            {25, 0.2, 0.3, 0.9, 2.0, 3.4, 5.0, 6.8, 8.8, 13.4},
            {30, 0.2, 0.5, 1.3, 3.1, 5.2, 7.7, 10.6, 13.7, 21.0},
            {40, 0.4, 0.9, 2.5, 5.9, 10.3, 15.4, 21.4, 28.0, 43.4},
            {50, 0.6, 1.4, 4.1, 10.0, 17.6, 26.7, 37.2, 49.1, 0}};

    //Sorghum
    private double[][] expSGstaicpresures = {{0.0, 0.1, 0.5, 1.0, 3.0, 5.0, 10.0},
            {1, 0.00, 0.01, 0.0, 0.06, 0.11, 0.23},
            {2, 0.01, 0.04, 0.08, 0.26, 0.45, 1.02},
            {3, 0.02, 0.09, 0.19, 0.61, 1.09, 2.54},
            {4, 0.03, 0.16, 0.33, 1.12, 2.04, 4.92},
            {5, 0.05, 0.26, 0.53, 1.81, 3.36, 8.29},
            {6, 0.07, 0.37, 0.8, 2.70, 5.08, 12.79},
            {7, 0.10, 0.51, 1.07, 3.80, 7.23, 0},
            {8, 0.13, 0.67, 1.42, 5.11, 9.84, 0},
            {9, 0.16, 0.85, 1.82, 6.67, 12.95, 0},
            {10, 0.20, 1.06, 2.27, 8.46, 0, 0},
            {11, 0.24, 1.29, 2.78, 10.52, 0, 0},
            {12, 0.29, 1.55, 3.36, 0, 0, 0},
            {13, 0.34, 1.83, 3.99, 0, 0, 0},
            {14, 0.39, 2.14, 4.68, 0, 0, 0},
            {15, 0.45, 2.47, 5.44, 0, 0, 0},
            {16, 0.52, 2.83, 6.26, 0, 0, 0}};

    //paddy depth (m), static pressure (cm of water) and air flow (m3/min per t)
    //expPRstaicpresures: {{0, 0.07, 0.14, 0.28},
    //                    {3, 0.7, 0.8, 0.9},
    //                    {6, 0.9, 1.2, 1.8},
    //                    {9, 1.3, 1.9, 3.3},
    //                    {12, 1.8, 2.9, 5.7},
    //                    {18, 3.4, 6.2, 13.5},
    //                    {24, 5.7, 11.5, 0.0}},
    //1cfm/bu = 1.0405m3/min-t
    //1 cm = 0.393701 inch
    //1 m = 3.28084 ft

    private double[][] expPRstaicpresures = {{0, 0.067275348, 0.134550697, 0.269101394},
            {9.84252, 0.2755907, 0.3149608, 0.3543309},
            {19.68504, 0.3543309, 0.4724412, 0.7086618},
            {29.52756, 0.5118113, 0.7480319, 1.2992133},
            {39.37008, 0.7086618, 1.1417329, 2.2440957},
            {59.05512, 1.3385834, 2.4409462, 5.3149635},
            {78.74016, 2.2440957, 4.5275615, 0}};

    //Axial-flow performance
    private double[][] axfhp = {{0, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0},
            {0.33, 1435, 620, 290, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
            {0.5, 1880, 960, 800, 620, 380, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
            {0.75, 1690, 1460, 1170, 780, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
            {1, 2775, 2500, 2075, 1150, 775, 500, 260, 0.00, 0.00, 0.00, 0.00},
            {1.5, 3675, 3475, 3275, 3000, 2425, 1700, 1375, 0.00, 0.00, 0.00, 0.00},
            {3, 6400, 5700, 5200, 4500, 3700, 2900, 2200, 0.00, 0.00, 0.00, 0.00},
            {5, 9600, 8550, 7600, 6800, 6150, 5300, 4200, 1550, 0.00, 0.00, 0.00},
            {7.5, 13400, 12500, 11500, 10400, 9000, 7500, 6200, 4450, 2250, 1350, 650},
            {10, 15700, 15000, 14200, 13400, 12600, 11600, 10500, 0.00, 0.00, 0.00, 0.00}};

    //Centrifugal fan performance
    //cffhp: {{0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0},
    //        {5, 7600, 6700, 5800, 4800, 3500, 1500, 0.00},
    //        {7.5, 9600, 8900, 8000, 7200, 6100, 5000, 0.00},
    //        {10, 13450, 12720, 11960, 11120, 10180, 9040, 7450},
    //        {15, 16000, 15100, 14200, 13100, 11800, 10000, 0.00},
    //        {20, 21725, 20430, 19140, 17750, 16140, 14120, 11360}},

    ///Table 3. Typical centrifugal fan air delivery ratings, cubic feet per minute (CFM)3
    //3Gardisser, D., and J. Langston. “Grain Drying and Psychrometric Chart.” Cooperative Extension Service,
    ////University of Arkansas, Little Rock, Arkansas, USA.
    private double[][] cffhp = {{0, 0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0},
            {5, 7200, 7000, 6700, 6300, 5900, 5500, 5000, 4400, 3800, 2500},
            {7.5, 10850, 10500, 10100, 9400, 8950, 8250, 7500, 6650, 5750, 3750},
            {10, 14500, 14000, 13500, 12500, 12000, 11000, 10000, 8900, 7700, 5000},
            {15, 16750, 16250, 15750, 14750, 14250, 13250, 12250, 11200, 10050, 8000},
            {20, 19000, 18500, 18000, 17000, 16500, 15500, 14500, 13500, 12400, 11000}};

    //FSA-Temp-RH-EWC.pdf
    //Corn Equilibrium Moisture Content
    //Relative Humidity (%)
    private double[][] maizeTMPRHEMC = {{0, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100},      //Temperature (°F)
            {25, 9.3, 9.1, 8.8, 8.6, 8.4, 8.2, 8, 7.9, 7.7, 7.6, 7.4, 7.3, 7.2, 7.1},
            {30, 10.3, 10, 9.8, 9.5, 9.3, 9.1, 8.9, 8.7, 8.5, 8.4, 8.2, 8.1, 7.9, 7.8},
            {35, 11.2, 10.9, 10.6, 10.4, 10.1, 9.9, 9.7, 9.5, 9.3, 9.1, 9, 8.8, 8.7, 8.5},
            {40, 12.1, 11.8, 11.5, 11.2, 11, 10.7, 10.5, 10.3, 10.1, 9.9, 9.7, 9.5, 9.4, 9.2},
            {45, 13, 12.7, 12.3, 12, 11.8, 11.5, 11.3, 11, 10.8, 10.6, 10.4, 10.3, 10.1, 9.9},
            {50, 13.9, 13.5, 13.2, 12.9, 12.6, 12.3, 12, 11.8, 11.6, 11.4, 11.2, 11, 10.8, 10.6},
            {55, 14.8, 14.4, 14, 13.7, 13.4, 13.1, 12.8, 12.6, 12.3, 12.1, 11.9, 11.7, 11.5, 11.3},
            {60, 15.7, 15.3, 14.9, 14.5, 14.2, 13.9, 13.6, 13.4, 13.1, 12.9, 12.6, 12.4, 12.2, 12},
            {65, 16.6, 16.2, 15.8, 15.4, 15.1, 14.8, 14.5, 14.2, 13.9, 13.7, 13.4, 13.2, 13, 12.8},
            {70, 17.6, 17.1, 16.7, 16.3, 16, 15.7, 15.3, 15, 14.8, 14.5, 14.3, 14, 13.8, 13.6},
            {75, 18.7, 18.2, 17.7, 17.3, 17, 16.6, 16.3, 16, 15.7, 15.4, 15.2, 14.9, 14.7, 14.5},
            {80, 19.8, 19.3, 18.9, 18.5, 18.1, 17.7, 17.4, 17, 16.7, 16.4, 16.2, 15.9, 15.6, 15.4},
            {85, 21.2, 20.7, 20.2, 19.8, 19.3, 18.9, 18.6, 18.2, 17.9, 17.6, 17.3, 17, 16.8, 16.5},
            {90, 22.9, 22.3, 21.8, 21.4, 20.9, 20.5, 20.1, 19.8, 19.4, 19.1, 18.8, 18.5, 18.2, 17.9}};

    //Soybean Equilibrium Moisture Content. Relative Humidity (%)
    private double[][] sybeanTMPRHEMC = {{0, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100},
            {25, 5.9, 5.8, 5.8, 5.7, 5.7, 5.6, 5.6, 5.5, 5.4, 5.4, 5.3, 5.3, 5.2, 5.2},
            {30, 6.5, 6.4, 6.4, 6.3, 6.2, 6.2, 6.1, 6.1, 6, 5.9, 5.9, 5.8, 5.7, 5.7},
            {35, 7.1, 7.1, 7, 6.9, 6.8, 6.8, 6.7, 6.6, 6.6, 6.5, 6.4, 6.4, 6.3, 6.2},
            {40, 7.8, 7.7, 7.7, 7.6, 7.5, 7.4, 7.4, 7.3, 7.2, 7.1, 7.1, 7, 6.9, 6.9},
            {45, 8.6, 8.5, 8.4, 8.3, 8.2, 8.1, 8.1, 8, 7.9, 7.8, 7.7, 7.7, 7.6, 7.5},
            {50, 9.4, 9.3, 9.2, 9.1, 9, 8.9, 8.9, 8.8, 8.7, 8.6, 8.5, 8.4, 8.3, 8.3},
            {55, 10.3, 10.2, 10.1, 10, 10, 9.9, 9.8, 9.7, 9.6, 9.5, 9.4, 9.3, 9.2, 9.1},
            {60, 11.5, 11.3, 11.2, 11.1, 11, 10.9, 10.8, 10.7, 10.6, 10.5, 10.4, 10.3, 10.2, 10.1},
            {65, 12.8, 12.6, 12.5, 12.4, 12.3, 12.2, 12.1, 11.9, 11.8, 11.7, 11.6, 11.5, 11.4, 11.3},
            {70, 14.4, 14.2, 14.1, 14, 13.8, 13.7, 13.6, 13.5, 13.3, 13.2, 13.1, 13, 12.8, 12.7},
            {75, 16.4, 16.2, 16.1, 16, 15.8, 15.7, 15.5, 15.4, 15.2, 15.1, 15, 14.8, 14.7, 14.5},
            {80, 19.1, 18.9, 18.7, 18.6, 18.4, 18.3, 18.1, 17.9, 17.8, 17.6, 17.5, 17.3, 17.1, 17},
            {85, 22.9, 22.7, 22.5, 22.3, 22.1, 21.9, 21.7, 21.6, 21.4, 21.2, 21, 20.8, 20.7, 20.5},
            {90, 28.9, 28.7, 28.4, 28.2, 28, 27.8, 27.6, 27.3, 27.1, 26.9, 26.7, 26.5, 26.3, 26.1}};

    //Sorghum Equilibrium Moisture Content. Relative Humidity (%)
    private double[][] sghumTMPRHEMC = {{0, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100},
            {25, 11.5, 11.3, 11.1, 10.9, 10.7, 10.5, 10.4, 10.2, 10, 9.9, 9.7, 9.6, 9.4, 9.3},
            {30, 12.1, 11.9, 11.7, 11.5, 11.3, 11.2, 11, 10.8, 10.6, 10.5, 10.3, 10.2, 10, 9.9},
            {35, 12.7, 12.5, 12.3, 12.1, 11.9, 11.7, 11.6, 11.4, 11.2, 11.1, 10.9, 10.8, 10.6, 10.5},
            {40, 13.3, 13.1, 12.9, 12.7, 12.5, 12.3, 12.2, 12, 11.8, 11.7, 11.5, 11.4, 11.2, 11.1},
            {45, 13.8, 13.6, 13.4, 13.3, 13.1, 12.9, 12.7, 12.6, 12.4, 12.3, 12.1, 12, 11.8, 11.7},
            {50, 14.4, 14.2, 14, 13.8, 13.7, 13.5, 13.3, 13.2, 13, 12.9, 12.7, 12.6, 12.4, 12.3},
            {55, 15, 14.8, 14.6, 14.4, 14.3, 14.1, 13.9, 13.8, 13.6, 13.5, 13.3, 13.2, 13.1, 12.9},
            {60, 15.6, 15.4, 15.3, 15.1, 14.9, 14.7, 14.6, 14.4, 14.3, 14.1, 14, 13.8, 13.7, 13.6},
            {65, 16.3, 16.1, 15.9, 15.7, 15.6, 15.4, 15.2, 15.1, 14.9, 14.8, 14.7, 14.5, 14.4, 14.3},
            {70, 17, 16.8, 16.6, 16.5, 16.3, 16.1, 16, 15.8, 15.7, 15.6, 15.4, 15.3, 15.2, 15},
            {75, 17.8, 17.6, 17.5, 17.3, 17.1, 17, 16.8, 16.7, 16.5, 16.4, 16.3, 16.1, 16, 15.9},
            {80, 18.8, 18.6, 18.4, 18.2, 18.1, 17.9, 17.8, 17.6, 17.5, 17.4, 17.2, 17.1, 17, 16.9},
            {85, 19.9, 19.7, 19.6, 19.4, 19.2, 19.1, 18.9, 18.8, 18.7, 18.5, 18.4, 18.3, 18.2, 18},
            {90, 21.4, 21.2, 21.1, 20.9, 20.8, 20.6, 20.5, 20.3, 20.2, 20.1, 20, 19.8, 19.7, 19.6}};

    //Rice Long Grain Equilibrium Moisture Content. Relative Humidity (%)
    private double[][] riceTMPRHEMC = {{0, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100},
            {25, 9.2, 9, 8.8, 8.6, 8.5, 8.3, 8.2, 8, 7.9, 7.8, 7.6, 7.5, 7.4, 7.3},
            {30, 10.1, 9.9, 9.7, 9.5, 9.3, 9.1, 8.9, 8.8, 8.7, 8.5, 8.4, 8.3, 8.1, 8},
            {35, 10.9, 10.7, 10.5, 10.3, 10.1, 9.9, 9.7, 9.5, 9.4, 9.2, 9.1, 9, 8.8, 8.7},
            {40, 11.7, 11.5, 11.2, 11, 10.8, 10.6, 10.4, 10.3, 10.1, 9.9, 9.8, 9.6, 9.5, 9.4},
            {45, 12.5, 12.3, 12, 11.8, 11.5, 11.3, 11.1, 11, 10.8, 10.6, 10.5, 10.3, 10.2, 10},
            {50, 13.3, 13, 12.8, 12.5, 12.3, 12.1, 11.9, 11.7, 11.5, 11.3, 11.1, 11, 10.8, 10.7},
            {55, 14.1, 13.8, 13.5, 13.3, 13, 12.8, 12.6, 12.4, 12.2, 12, 11.8, 11.6, 11.5, 11.3},
            {60, 14.9, 14.6, 14.3, 14, 13.8, 13.5, 13.3, 13.1, 12.9, 12.7, 12.5, 12.3, 12.2, 12},
            {65, 15.7, 15.4, 15.1, 14.8, 14.5, 14.3, 14.1, 13.8, 13.6, 13.4, 13.2, 13, 12.9, 12.7},
            {70, 16.6, 16.3, 15.9, 15.7, 15.4, 15.1, 14.9, 14.6, 14.4, 14.2, 14, 13.8, 13.6, 13.4},
            {75, 17.6, 17.2, 16.9, 16.5, 16.3, 16, 15.7, 15.5, 15.2, 15, 14.8, 14.6, 14.4, 14.2},
            {80, 18.6, 18.2, 17.9, 17.5, 17.2, 16.9, 16.7, 16.4, 16.2, 15.9, 15.7, 15.5, 15.3, 15.1},
            {85, 19.8, 19.4, 19, 18.7, 18.4, 18.1, 17.8, 17.5, 17.2, 17, 16.8, 16.5, 16.3, 16.1},
            {90, 21.3, 20.9, 20.5, 20.1, 19.8, 19.5, 19.2, 18.9, 18.6, 18.3, 18.1, 17.8, 17.6, 17.4}};

    private List<String> randNOSList = new ArrayList<>();
    private List<String> relhEWCList = new ArrayList<>();
    private List<String> fansSELList = new ArrayList<>();
    private List<String> pcidUSEList = new ArrayList<>();
    private List<String> allrecordsList = new ArrayList<>();
    private String storageRandNOSRecs = "";
    private String storageRelhEWCRecs = "";
    private String storageFansSELRecs = "";
    private String storagePcidUSERecs = "";
    private String storageAllRecords = "";
    private static SiloCom INSTANCE = null;
    private Context context;
    private SiloCom(Context context) {
        this.context = context;
    }

    public static SiloCom getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SiloCom(context);
        }
        return INSTANCE;
    }

    private static <T> Set<T> convertArrayToSet(T array[]) {

        // Create an empty Set
        Set<T> set = new HashSet<>();
        // Iterate through the array
        for (T t : array) {
            // Add each element into the set
            set.add(t);
        }
        // Return the converted Set
        return set;
    }

    private int[] bubbleSort(Set<Integer> arr){
        Object[] intsarr = arr.toArray();
        int arrno = intsarr.length;
        int[] numarrary = new int[arrno];
        for(int k = 0; k < arrno; k++){
            numarrary[k] = (int)intsarr[k];
        }
        int len = intsarr.length;
        for (int i = (len - 1); i >= 0; i--){
            for(int j = 1; j <= i; j++){
                if( numarrary[j - 1] >  numarrary[j]){
                    int temp =  numarrary[j - 1];
                    numarrary[j - 1] =  numarrary[j];
                    numarrary[j] = temp;
                }
            }
        }
        // convert the Array to Set
        //Set<Integer> intsset = convertArrayToSet(intsarr);
        return numarrary;
    }

    public String randomNos(String params) {
        String[] itemsArr = UtilFuncs.getStringArray(params);
        String compstatus = "";
        Set<Integer> temprandomset = new HashSet<>();
        String numbagsStr = itemsArr[0];
        int numbags = Integer.valueOf(numbagsStr);
        int notoselect = 0;
        if(numbags <= 10){
            notoselect = numbags;
            for(int i = 1; i <= notoselect; i++){
                temprandomset.add(i);
            }
        }else if(numbags >= 11 && numbags <= 100){
            notoselect = 10;
            int min = 1;
            int max = numbags;
            int i = 0;
            while(i < 10){
                double tempval = Math.random() * (max - min + 1);
                double randmval = Math.floor(tempval) + min;
                String randvalstr = Double.toString(randmval);
                double dbrandval = Double.valueOf(randvalstr);
                int randval = (int)dbrandval;
                int templn = temprandomset.size();
                boolean seen = false;
                if(templn >= 1){
                    for(int j = 0; j < templn; j++){
                        if(temprandomset.contains(randval)){
                            seen = true;
                        }
                    }
                    if(!seen){
                        temprandomset.add(randval);
                        i++;
                    }
                }else if(templn == 0){
                    temprandomset.add(randval);
                    i++;
                }
                templn = temprandomset.size();
                if(templn == notoselect){
                    break;
                }
            }
        }else if(numbags >= 101 && numbags <= 10000){
            double sqrval = Math.sqrt(numbags);
            double dflval = Math.floor(sqrval);
            String flvalstr = Double.toString(dflval);
            double dbflval = Double.valueOf(flvalstr);
            int flval = (int)dbflval;
            int totalnum = flval*flval;
            if(numbags > (flval * flval)){
                notoselect = flval + 1;
            }else if(numbags == (flval * flval)){
                notoselect = flval;
            }
            int min = 1;
            int max = min + flval - 1;
            while(max <= totalnum){
                double tempval = Math.random() * (max - min + 1);
                double randmval = Math.floor(tempval) + min;
                String randvalstr = Double.toString(randmval);
                double dbrandval = Double.valueOf(randvalstr);
                int randval = (int)dbrandval;
                temprandomset.add(randval);
                min = max + 1;
                max = min + flval - 1;
            }
            if(notoselect > flval){
                min = totalnum + 1;     //48 - 59 => 11
                max = min + ((numbags - totalnum) - 1);
                double tempval = Math.random() * (max - min + 1);
                double drandval = Math.floor(tempval) + min;
                String randvalstr = Double.toString(drandval);
                double dbrandval = Double.valueOf(randvalstr);
                int randval = (int)dbrandval;
                temprandomset.add(randval);
            }
        }
        int[] randsetsorted = bubbleSort(temprandomset);
        String randsetStr = "[ ";
        for(int i = 0; i < randsetsorted.length; i++){
            randsetStr = randsetStr + randsetsorted[i] + " ";
        }
        randsetStr = randsetStr + "]";

        String randomResult = "";
        randomResult += "Nos. of bags: " + numbagsStr + "|";
        randomResult += "Random set: " + randsetStr + "|";
        if(!randomResult.isEmpty()) {
            String[] arrelems = UtilFuncs.barDelstringToArray(randomResult);
            String randrecsStr = outerToJson(arrelems);
            randNOSList.add(randrecsStr + "<br>");
            compstatus = "Computation successful";
        }else {
            compstatus = "Computation unsuccessful";
        }
        return compstatus;
    }

    public String relHumdEqmc(String params) {
        String compstatus = "";
        String[] itemsArr = UtilFuncs.getStringArray(params);
        String cropname = itemsArr[0];
        String wetbulbStr = itemsArr[1];
        String drybulbStr = itemsArr[2];
        double wetbulb = Double.valueOf(wetbulbStr);
        double drybulb = Double.valueOf(drybulbStr);
        double e = 2.71828182845904;
        double N = 0.6687451584;
        double ednum = 17.502 * drybulb;
        double eddnum = 240.97 + drybulb;
        double ed = 6.112 * e *(ednum/eddnum);
        double ewnum = 17.502 * wetbulb;
        double ewdnum = 240.97 + wetbulb;
        double ew = 6.112 * e *(ewnum/ewdnum);
        double rlhumd = ((ew - N*(1 + 0.00115*wetbulb)*(drybulb - wetbulb))/ed)*100;
        double relhumd = Precision.round(rlhumd, 2);
        double drybulbtp = Math.floor((drybulb * 9/5) + 32);
        double drybulbtemp = Precision.round(drybulbtp, 2);       //Double.valueOf(result);
        double cropEMC = 0.00;
        boolean quitloop = false;
        if("maize".equals(cropname)){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsCORN = maizeTMPRHEMC.length;
            int i = 0;
            int j = 1;
            quitloop = false;
             boolean relhumdOK = false;
            while(j < (maizeTMPRHEMC[i].length - 1)){
                if(drybulbtemp == maizeTMPRHEMC[i][j]){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(drybulbtemp == Precision.round(maizeTMPRHEMC[i][j + 1], 2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((drybulbtemp > Precision.round(maizeTMPRHEMC[i][j], 2)) && (drybulbtemp < Precision.round(maizeTMPRHEMC[i][j + 1], 2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }

            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsCORN - 1)){
                    if(relhumd == Precision.round(maizeTMPRHEMC[i][j], 2)){
                        sprow = i;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if(relhumd == Precision.round(maizeTMPRHEMC[i + 1][j], 2)){
                        sprow = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if((relhumd > Precision.round(maizeTMPRHEMC[i][j], 2)) && (relhumd < Precision.round(maizeTMPRHEMC[i + 1][j], 2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }
                    i++;
                }
                if(quitloop && relhumdOK){
                    if((sprow2 > 0) && (spcolumn2 > 0)){
                        cropEMC = maizeTMPRHEMC[sprow2][spcolumn2];
                    }else if((sprow2 == 0) && (spcolumn2 > 0)){
                        cropEMC = maizeTMPRHEMC[sprow][spcolumn2];
                    }else if((sprow2 > 0) && (spcolumn2 == 0)){
                        cropEMC = maizeTMPRHEMC[sprow2][spcolumn];
                    }else if((sprow2 == 0) && (spcolumn2 == 0)){
                        cropEMC = maizeTMPRHEMC[sprow][spcolumn];
                    }
                }
            }

        }else if("soyabean".equals(cropname)){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsCORN = sybeanTMPRHEMC.length;
            int i = 0;
            int j = 1;
            quitloop = false;
            boolean relhumdOK = false;
            while(j < (sybeanTMPRHEMC[i].length - 1)){
                if(drybulbtemp == sybeanTMPRHEMC[i][j]){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(drybulbtemp == Precision.round(sybeanTMPRHEMC[i][j + 1], 2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((drybulbtemp > Precision.round(sybeanTMPRHEMC[i][j], 2)) && (drybulbtemp < Precision.round(sybeanTMPRHEMC[i][j + 1], 2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }
            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsCORN - 1)){
                    if(relhumd == Precision.round(sybeanTMPRHEMC[i][j], 2)){
                        sprow = i;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if(relhumd == Precision.round(sybeanTMPRHEMC[i + 1][j], 2)){
                        sprow = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if((relhumd > Precision.round(sybeanTMPRHEMC[i][j], 2)) && (relhumd < Precision.round(sybeanTMPRHEMC[i + 1][j], 2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }
                    i++;
                }
                if(quitloop && relhumdOK){
                    if((sprow2 > 0) && (spcolumn2 > 0)){
                        cropEMC = sybeanTMPRHEMC[sprow2][spcolumn2];
                    }else if((sprow2 == 0) && (spcolumn2 > 0)){
                        cropEMC = sybeanTMPRHEMC[sprow][spcolumn2];
                    }else if((sprow2 > 0) && (spcolumn2 == 0)){
                        cropEMC = sybeanTMPRHEMC[sprow2][spcolumn];
                    }else if((sprow2 == 0) && (spcolumn2 == 0)){
                        cropEMC = sybeanTMPRHEMC[sprow][spcolumn];
                    }
                }
            }

        }else if("sorghum".equals(cropname)){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsCORN = sghumTMPRHEMC.length;
            int i = 0;
            int j = 1;
            quitloop = false;
            boolean relhumdOK = false;
            while(j < (sghumTMPRHEMC[i].length - 1)){
                if(drybulbtemp == sghumTMPRHEMC[i][j]){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(drybulbtemp == Precision.round(sghumTMPRHEMC[i][j + 1], 2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((drybulbtemp > Precision.round(sghumTMPRHEMC[i][j], 2)) && (drybulbtemp < Precision.round(sghumTMPRHEMC[i][j + 1], 2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }
            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsCORN - 1)){
                    if(relhumd == Precision.round(sghumTMPRHEMC[i][j], 2)){
                        sprow = i;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if(relhumd == Precision.round(sghumTMPRHEMC[i + 1][j], 2)){
                        sprow = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if((relhumd > Precision.round(sghumTMPRHEMC[i][j], 2)) && (relhumd < Precision.round(sghumTMPRHEMC[i + 1][j], 2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }
                    i++;
                }
                if(quitloop && relhumdOK){
                    if((sprow2 > 0) && (spcolumn2 > 0)){
                        cropEMC = sghumTMPRHEMC[sprow2][spcolumn2];
                    }else if((sprow2 == 0) && (spcolumn2 > 0)){
                        cropEMC = sghumTMPRHEMC[sprow][spcolumn2];
                    }else if((sprow2 > 0) && (spcolumn2 == 0)){
                        cropEMC = sghumTMPRHEMC[sprow2][spcolumn];
                    }else if((sprow2 == 0) && (spcolumn2 == 0)){
                        cropEMC = sghumTMPRHEMC[sprow][spcolumn];
                    }
                }
            }

        }else if("paddyrice".equals(cropname)){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsCORN = riceTMPRHEMC.length;
            int i = 0;
            int j = 1;
            quitloop = false;
            boolean relhumdOK = false;
            while(j < (riceTMPRHEMC[i].length - 1)){
                if(drybulbtemp == riceTMPRHEMC[i][j]){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(drybulbtemp == Precision.round(riceTMPRHEMC[i][j + 1], 2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((drybulbtemp > Precision.round(riceTMPRHEMC[i][j], 2)) && (drybulbtemp < Precision.round(riceTMPRHEMC[i][j + 1], 2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }
            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsCORN - 1)){
                    if(relhumd == Precision.round(riceTMPRHEMC[i][j], 2)){
                        sprow = i;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if(relhumd == Precision.round(riceTMPRHEMC[i + 1][j], 2)){
                        sprow = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }else if((relhumd > Precision.round(riceTMPRHEMC[i][j], 2)) && (relhumd < Precision.round(riceTMPRHEMC[i + 1][j], 2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        relhumdOK = true;
                        break;
                    }
                    i++;
                }
            }
            if(quitloop && relhumdOK){
                if((sprow2 > 0) && (spcolumn2 > 0)){
                    cropEMC = riceTMPRHEMC[sprow2][spcolumn2];
                }else if((sprow2 == 0) && (spcolumn2 > 0)){
                    cropEMC = riceTMPRHEMC[sprow][spcolumn2];
                }else if((sprow2 > 0) && (spcolumn2 == 0)){
                    cropEMC = riceTMPRHEMC[sprow2][spcolumn];
                }else if((sprow2 == 0) && (spcolumn2 == 0)){
                    cropEMC = riceTMPRHEMC[sprow][spcolumn];
                }
            }
        }

        if(quitloop){
            String relhumdStr = Double.toString(relhumd);
            String cropEMCStr = Double.toString(cropEMC);
            String relhmdResult = "";
            relhmdResult += "Name of grain: " + cropname + "|";
            relhmdResult += "Wet bulb temperature: " + wetbulbStr + "|";
            relhmdResult += "Dry bulb temperature: " + drybulbStr + "|";
            relhmdResult += "Relative humidity: " + relhumdStr + "|";
            relhmdResult += "Equilibrium moisture content: " + cropEMCStr + "|";
            if(!relhmdResult.isEmpty()) {
                String[] arrelems = UtilFuncs.barDelstringToArray(relhmdResult);
                String relhmrecsStr = outerToJson(arrelems);
                relhEWCList.add(relhmrecsStr + "<br>");
                compstatus = "Computation successful";
            }else {
                compstatus = "Computation unsuccessful";
            }
        }else{
            compstatus = "Out of range for some entries in the table.";
        }
        return compstatus;
    }

    public String fansSelect(String params) {
        String compstatus = "";
        String[] itemsArr = UtilFuncs.getStringArray(params);
        String grainname = itemsArr[0];
        String unitofmeasure = itemsArr[1];
        String bindiameterStr = itemsArr[2];
        String binheightStr = itemsArr[3];
        String desiredairflowStr = itemsArr[4];
        String floortype = itemsArr[5];
        String axialOrcentrifugal = itemsArr[6];
        double bindiameter = Double.valueOf(bindiameterStr);
        double binheight = Double.valueOf(binheightStr);
        double desiredairflow = Double.valueOf(desiredairflowStr);
        double binCapacity = 0.0;
        // If you want to supply 1 cubic feet of air per minute per bushel (cfm/bu)
        double totalAirflow = 0.0;
        double bindiameterSI = 0.0;
        double binheightSI = 0.0;
        double desiredairflowSI = 0.0;
        double binCapacitySI = 0.0;
        double totalAirflowSI = 0.0;
        if(unitofmeasure.equals("si-unit")){
            bindiameterSI = bindiameter;
            binheightSI = binheight;
            desiredairflowSI = desiredairflow*0.0134;
            bindiameter = bindiameter/0.3048;
            binheight = binheight/0.3048;
            binCapacity = ((Math.PI)/4)*(bindiameter*bindiameter)*binheight*0.8;  // bu/cubic ft.
            // If you want to supply 1 cubic feet of air per minute per bushel (cfm/bu)
            totalAirflow = Math.floor(desiredairflow*binCapacity);
            binCapacitySI = binCapacity*(0.03523907/0.02831685);
            totalAirflowSI = totalAirflow*(0.00047/0.02831685);  //cfm/bu*bu/cubic ft = cfm/cubic ft cubic m/s/cubic m
        }else if(unitofmeasure.equals("imperial-unit")){
            binCapacity = ((Math.PI)/4)*(bindiameter*bindiameter)*binheight*0.8;  // bu/cubic ft.
            // If you want to supply 1 cubic feet of air per minute per bushel (cfm/bu)
            totalAirflow = Math.floor(desiredairflow*binCapacity);
        }

        double fanpower = 0;
        double staticPressure = 0;
        boolean quitloop = false;
        if(grainname.equals("maize")){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsSC = expSCstaicpresures.length;

            int i = 0;
            int j = 1;
            quitloop = false;
            boolean binheightOK = false;
            while(j < (expSCstaicpresures[i].length - 1)){
                if(Precision.round(desiredairflow,2) == Precision.round(expSCstaicpresures[i][j],2)){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(Precision.round(desiredairflow,2) == Precision.round(expSCstaicpresures[i][j + 1],2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((Precision.round(desiredairflow,2) > Precision.round(expSCstaicpresures[i][j],2)) && (Precision.round(desiredairflow,2) < Precision.round(expSCstaicpresures[i][j + 1],2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }

            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsSC - 1)){
                    if(Precision.round(binheight,2) == Precision.round(expSCstaicpresures[i][j],2)){
                        sprow = i;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if(Precision.round(binheight,2) == Precision.round(expSCstaicpresures[i + 1][j],2)){
                        sprow = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if((Precision.round(binheight,2) > Precision.round(expSCstaicpresures[i][j],2)) && (Precision.round(binheight,2) < Precision.round(expSCstaicpresures[i + 1][j],2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }
                    i++;
                }
            }

            if(quitloop && binheightOK){
                if((sprow2 > 0) && (spcolumn2 > 0)){
                    staticPressure = expSCstaicpresures[sprow2][spcolumn2];
                }else if((sprow2 == 0) && (spcolumn2 > 0)){
                    staticPressure = expSCstaicpresures[sprow][spcolumn2];
                }else if((sprow2 > 0) && (spcolumn2 == 0)){
                    staticPressure = expSCstaicpresures[sprow2][spcolumn];
                }else if((sprow2 == 0) && (spcolumn2 == 0)){
                    staticPressure = expSCstaicpresures[sprow][spcolumn];
                }
            }
        }else if(grainname.equals("soyabean")){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsSC = expSBstaicpresures.length;

            int i = 0;
            int j = 1;
            quitloop = false;
            boolean binheightOK = false;
            while(j < (expSBstaicpresures[i].length - 1)){
                if(Precision.round(desiredairflow,2) == Precision.round(expSBstaicpresures[i][j],2)){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(Precision.round(desiredairflow,2) == Precision.round(expSBstaicpresures[i][j + 1],2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((Precision.round(desiredairflow,2) > Precision.round(expSBstaicpresures[i][j],2)) && (Precision.round(desiredairflow,2) < Precision.round(expSBstaicpresures[i][j + 1],2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }

            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsSC - 1)){
                    if(Precision.round(binheight,2) == Precision.round(expSBstaicpresures[i][j],2)){
                        sprow = i;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if(Precision.round(binheight,2) == Precision.round(expSBstaicpresures[i + 1][j],2)){
                        sprow = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if((Precision.round(binheight,2) > Precision.round(expSBstaicpresures[i][j],2)) && (Precision.round(binheight,2) < Precision.round(expSBstaicpresures[i + 1][j],2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }
                    i++;
                }
            }

            if(quitloop && binheightOK){
                if((sprow2 > 0) && (spcolumn2 > 0)){
                    staticPressure = expSBstaicpresures[sprow2][spcolumn2];
                }else if((sprow2 == 0) && (spcolumn2 > 0)){
                    staticPressure = expSBstaicpresures[sprow][spcolumn2];
                }else if((sprow2 > 0) && (spcolumn2 == 0)){
                    staticPressure = expSBstaicpresures[sprow2][spcolumn];
                }else if((sprow2 == 0) && (spcolumn2 == 0)){
                    staticPressure = expSBstaicpresures[sprow][spcolumn];
                }
            }
        }else if(grainname.equals("sorghum")){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsSC = expSGstaicpresures.length;

            int i = 0;
            int j = 1;
            quitloop = false;
            boolean binheightOK = false;
            while(j < (expSGstaicpresures[i].length - 1)){
                if(Precision.round(desiredairflow,2) == Precision.round(expSGstaicpresures[i][j],2)){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(Precision.round(desiredairflow,2) == Precision.round(expSGstaicpresures[i][j + 1],2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((Precision.round(desiredairflow,2) > Precision.round(expSGstaicpresures[i][j],2)) && (Precision.round(desiredairflow,2) < Precision.round(expSGstaicpresures[i][j + 1],2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }

            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsSC - 1)){
                    if(Precision.round(binheight,2) == Precision.round(expSGstaicpresures[i][j],2)){
                        sprow = i;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if(Precision.round(binheight,2) == Precision.round(expSGstaicpresures[i + 1][j],2)){
                        sprow = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if((Precision.round(binheight,2) > Precision.round(expSGstaicpresures[i][j],2)) && (Precision.round(binheight,2) < Precision.round(expSGstaicpresures[i + 1][j],2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }
                    i++;
                }
            }

            if(quitloop && binheightOK){
                if((sprow2 > 0) && (spcolumn2 > 0)){
                    staticPressure = expSGstaicpresures[sprow2][spcolumn2];
                }else if((sprow2 == 0) && (spcolumn2 > 0)){
                    staticPressure = expSGstaicpresures[sprow][spcolumn2];
                }else if((sprow2 > 0) && (spcolumn2 == 0)){
                    staticPressure = expSGstaicpresures[sprow2][spcolumn];
                }else if((sprow2 == 0) && (spcolumn2 == 0)){
                    staticPressure = expSGstaicpresures[sprow][spcolumn];
                }
            }
        }else if(grainname.equals("paddyrice")){
            int sprow = 0;
            int sprow2 = 0;
            int spcolumn = 0;
            int spcolumn2 = 0;
            int nrowsSC = expPRstaicpresures.length;

            int i = 0;
            int j = 1;
            quitloop = false;
            boolean binheightOK = false;
            while(j < (expPRstaicpresures[i].length - 1)){
                if(Precision.round(desiredairflow,2) == Precision.round(expPRstaicpresures[i][j],2)){
                    spcolumn = j;
                    quitloop = true;
                    break;
                }else if(Precision.round(desiredairflow,2) == Precision.round(expPRstaicpresures[i][j + 1],2)){
                    spcolumn = j + 1;
                    quitloop = true;
                    break;
                }else if((Precision.round(desiredairflow,2) > Precision.round(expPRstaicpresures[i][j],2)) && (Precision.round(desiredairflow,2) < Precision.round(expPRstaicpresures[i][j + 1],2))){
                    spcolumn = j;
                    spcolumn2 = j + 1;
                    quitloop = true;
                    break;
                }
                j++;
            }

            if(quitloop){
                i = 1;
                j = 0;
                quitloop = false;
                while(i < (nrowsSC - 1)){
                    if(Precision.round(binheight,2) == Precision.round(expPRstaicpresures[i][j],2)){
                        sprow = i;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if(Precision.round(binheight,2) == Precision.round(expPRstaicpresures[i + 1][j],2)){
                        sprow = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }else if((Precision.round(binheight,2) > Precision.round(expPRstaicpresures[i][j],2)) && (Precision.round(binheight,2) < Precision.round(expPRstaicpresures[i + 1][j],2))){
                        sprow = i;
                        sprow2 = i + 1;
                        quitloop = true;
                        binheightOK = true;
                        break;
                    }
                    i++;
                }
            }

            if(quitloop && binheightOK){
                if((sprow2 > 0) && (spcolumn2 > 0)){
                    staticPressure = expPRstaicpresures[sprow2][spcolumn2];
                }else if((sprow2 == 0) && (spcolumn2 > 0)){
                    staticPressure = expPRstaicpresures[sprow][spcolumn2];
                }else if((sprow2 > 0) && (spcolumn2 == 0)){
                    staticPressure = expPRstaicpresures[sprow2][spcolumn];
                }else if((sprow2 == 0) && (spcolumn2 == 0)){
                    staticPressure = expPRstaicpresures[sprow][spcolumn];
                }
            }
        }

        if(quitloop) {
            if (floortype.equals("ductsystem")) {
                staticPressure = staticPressure + 0.5;
            }
            fanpower = (totalAirflow * staticPressure) / 3814;
            double fanHPSelected = 0.00;
            String fantype = "";
            boolean centrifugalFS = false;
            boolean axialFS = false;
            if (!axialOrcentrifugal.equals("fans-axial")) {
                int colb1 = 0;
                int colb2 = 0;
                int i = 0;
                for (int j = 0; j < axfhp[i].length; j++) {
                    if (axfhp[i][j] >= staticPressure) {
                        colb1 = j - 1;
                        colb2 = j;
                        break;
                    }
                }
                int nrowsAXF = axfhp.length;
                for (i = 1; i < nrowsAXF; i++) {
                    if ((axfhp[i][colb1] >= totalAirflow) && (axfhp[i][colb2] < totalAirflow)) {
                        fanHPSelected = axfhp[i][0];
                        fantype = "Axial";
                        break;
                    }
                }

                colb1 = 0;
                colb2 = 0;
                i = 0;
                boolean axialSP = false;
                for (int j = 2; j < axfhp[i].length; j++) {
                    if (Precision.round(axfhp[i][j], 2) > Precision.round(staticPressure, 2)) {
                        colb1 = j - 1;
                        colb2 = j;
                        axialSP = true;
                        break;
                    } else if (Precision.round(staticPressure, 2) == Precision.round(axfhp[i][j], 2)) {
                        colb1 = j;
                        axialSP = true;
                        break;
                    }
                }

                if (axialSP) {
                    nrowsAXF = axfhp.length;
                    for (i = 1; i < nrowsAXF; i++) {
                        if (Precision.round(axfhp[i][colb1], 2) > Precision.round(totalAirflow, 2)) {
                            fanHPSelected = axfhp[i][0];
                            fantype = "Axial";
                            axialFS = true;
                            break;
                        } else if (Precision.round(totalAirflow, 2) == Precision.round(axfhp[i][colb1], 2)) {
                            fanHPSelected = axfhp[i][0];
                            fantype = "Axial";
                            axialFS = true;
                            break;
                        } else if (Precision.round(axfhp[i][colb2], 2) > Precision.round(totalAirflow, 2)) {
                            fanHPSelected = axfhp[i][0];
                            fantype = "Axial";
                            axialFS = true;
                            break;
                        } else if (Precision.round(totalAirflow, 2) == Precision.round(axfhp[i][colb2], 2)) {
                            fanHPSelected = axfhp[i][0];
                            fantype = "Axial";
                            axialFS = true;
                            break;
                        }
                    }
                }
            } else if (!axialOrcentrifugal.equals("fans-centrifugal")) {
                int colb1 = 0;
                int colb2 = 0;
                int i = 0;
                for (int j = 0; j < cffhp[i].length; j++) {
                    if (axfhp[i][j] >= staticPressure) {
                        colb1 = j - 1;
                        colb2 = j;
                        break;
                    }
                }
                int nrowsCFF = cffhp.length;
                for (i = 1; i < nrowsCFF; i++) {
                    if ((cffhp[i][colb1] >= totalAirflow) && (cffhp[i][colb2] < totalAirflow)) {
                        fanHPSelected = cffhp[i][0];
                        fantype = "Centrifugal";
                        break;
                    }
                }

                colb1 = 0;
                colb2 = 0;
                i = 0;
                boolean centrifugalSP = false;
                for (int j = 2; j < cffhp[i].length; j++) {
                    if (Precision.round(cffhp[i][j], 2) > Precision.round(staticPressure, 2)) {
                        colb1 = j - 1;
                        colb2 = j;
                        centrifugalSP = true;
                        break;
                    } else if (Precision.round(staticPressure, 2) == Precision.round(cffhp[i][j], 2)) {
                        colb1 = j;
                        centrifugalSP = true;
                        break;
                    }
                }

                if (centrifugalSP) {
                    nrowsCFF = cffhp.length;
                    for (i = 1; i < nrowsCFF; i++) {
                        if (Precision.round(cffhp[i][colb1], 2) > Precision.round(totalAirflow, 2)) {
                            fanHPSelected = cffhp[i][0];
                            fantype = "Centrifugal";
                            centrifugalFS = true;
                            break;
                        } else if (Precision.round(totalAirflow, 2) == Precision.round(cffhp[i][colb1], 2)) {
                            fanHPSelected = cffhp[i][0];
                            fantype = "Centrifugal";
                            centrifugalFS = true;
                            break;
                        } else if (Precision.round(cffhp[i][colb2], 2) > Precision.round(totalAirflow, 2)) {
                            fanHPSelected = cffhp[i][0];
                            fantype = "Centrifugal";
                            centrifugalFS = true;
                            break;
                        } else if (Precision.round(totalAirflow, 2) == Precision.round(cffhp[i][colb2], 2)) {
                            fanHPSelected = cffhp[i][0];
                            fantype = "Centrifugal";
                            centrifugalFS = true;
                            break;
                        }
                    }
                }
            }

            if ((axialFS || centrifugalFS) || (axialFS && centrifugalFS)) {
                double fanHPSelectedSI = 0.0;
                double staticPressureSI = 0.0; //cm of water
                double fanpowerSI = 0.0; //N.m/s

                double valuestrSI;
                String valuestr;
                String fanselection = "";
                if (unitofmeasure.equals("si-unit")) {
                    fanHPSelectedSI = fanHPSelected * 735.49875;
                    staticPressureSI = staticPressure * 2.54; //cm of water
                    fanpowerSI = fanpower * (1000 / 1.340483); // watts         fanpower*735.49875; //N.m/s
                    valuestrSI = fanHPSelectedSI;
                    String fanHPSelectedSIStr = valuestrSI + "  watts (" + fantype + ")";
                    String bindiameterSIStr = bindiameterSI + " m";
                    String binheightSIStr = binheightSI + " m";
                    String desiredairflowSIStr = desiredairflowSI + " /s";
                    String binCapacitySIStr = Precision.round(binCapacitySI, 2) + " cubic m/cubic m";
                    String totalAirflowSIStr = totalAirflowSI + " cubic m/s";
                    String staticPressureSIStr = staticPressureSI + " cm of water";
                    String fanpowerSIStr = Precision.round(fanpowerSI, 2) + " watts";
                    fanselection += "Grain: " + grainname + "|";
                    fanselection += "Bin Diameter: " + bindiameterSIStr + "|";
                    fanselection += "Bin Height: " + binheightSIStr + "|";
                    fanselection += "Desired airflow: " + desiredairflowSIStr + "|";
                    fanselection += "Total airflow: " + totalAirflowSIStr + "|";
                    fanselection += "Floor Type: " + floortype + "|";
                    fanselection += "Bin capacity: " + binCapacitySIStr + "|";
                    fanselection += "Estimated static pressure: " + staticPressureSIStr + "|";
                    fanselection += "Estimated fan power needed: " + fanpowerSIStr + "|";
                    fanselection += "Fan Selected: " + fanHPSelectedSIStr + "|";
                } else if (unitofmeasure.equals("imperial-unit")) {
                    valuestr = Double.toString(fanHPSelected);
                    String fanHPSelectedStr = valuestr + " hp (" + fantype + ")";
                    bindiameterStr = bindiameter + " ft";
                    binheightStr = binheight + " ft";
                    desiredairflowStr = desiredairflow + " cfm/bu";
                    String binCapacityStr = Precision.round(binCapacity, 2) + " bu/cubic ft";
                    String totalAirflowStr = totalAirflow + " cfm";
                    String staticPressureStr = staticPressure + " inches of water";
                    String fanpowerStr = Precision.round(fanpower, 2) + " hp";
                    fanselection += "Grain: " + grainname + "|";
                    fanselection += "Bin Diameter: " + bindiameterStr + "|";
                    fanselection += "Bin Height: " + binheightStr + "|";
                    fanselection += "Desired airflow: " + desiredairflowStr + "|";
                    fanselection += "Total airflow: " + totalAirflowStr + "|";
                    fanselection += "Floor Type: " + floortype + "|";
                    fanselection += "Bin capacity: " + binCapacityStr + "|";
                    fanselection += "Estimated static pressure: " + staticPressureStr + "|";
                    fanselection += "Estimated fan power needed: " + fanpowerStr + "|";
                    fanselection += "Fan Selected: " + fanHPSelectedStr + "|";
                }

                if (!fanselection.isEmpty()) {
                    String[] arrelems = UtilFuncs.barDelstringToArray(fanselection);
                    String fansrecsStr = outerToJson(arrelems);
                    fansSELList.add(fansrecsStr + "<br>");
                    compstatus = "Computation successful";
                } else {
                    compstatus = "Computation unsuccessful";
                }
            } else {
                compstatus = "No appropriate fan found.";
            }
        }else{
            compstatus = "Desired airflow greater than highest in the table.";
        }
        return compstatus;
    }

    public String pesticideDoses(String params) {
        String compstatus = "";
        String[] itemsArr = UtilFuncs.getStringArray(params);
        String structure = itemsArr[0];
        double totalVol = 0;
        //30 tablets for 28.32 m3.
        double spaceFor30Tabletscubic_meter = 28.32;
        int noOfTabletsRequired = 0;
        int noOfCases = 0;
        int noOfFlasks = 0;
        int noOfTablets = 0;
        String outputStr = "";
        String struct = "";
        String lengthcbStr = "", widthcbStr = "", heightcbStr = "", heightpStr = "";
        double lengthcb,widthcb, heightcb,heightp;
        String lengthcoStr = "", widthcoStr = "", heightcoStr = "";
        double lengthco, widthco, heightco;
        String basefcStr = "", heightfcStr = "", lengthfcStr = "";
        double basefc, heightfc, lengthfc;
        String diameterclStr = "", heightclStr = "", heightcnStr = "";
        double diametercl, heightcl, heightcn;
        String diameterscStr = "", heightscStr = "";
        double diametersc, heightsc;
        if("volume-scenario1".equals(structure)){
            lengthcbStr = itemsArr[1];
            widthcbStr = itemsArr[2];
            heightcbStr = itemsArr[3];
            heightpStr = itemsArr[4];
            lengthcb = Double.parseDouble(lengthcbStr);
            heightcb = Double.parseDouble(heightcbStr);
            widthcb = Double.parseDouble(widthcbStr);
            heightp = Double.parseDouble(heightpStr);
            double volcuboid = lengthcb*widthcb*heightcb;
            double volprism = 0.5*widthcb*heightp*lengthcb;
            totalVol = volcuboid + volprism;
            double hdvalue = (totalVol*30)/spaceFor30Tabletscubic_meter;
            totalVol = Precision.round(totalVol,2);
            String hdvalueStr = Double.toString(hdvalue);
            double noftbrqed = Double.parseDouble(hdvalueStr);
            noOfTabletsRequired = (int)noftbrqed;
            struct = "Warehouse";
        }else if("volume-scenario2".equals(structure)){
            lengthcoStr = itemsArr[1];
            widthcoStr = itemsArr[2];
            heightcoStr = itemsArr[3];
            lengthco = Double.parseDouble(lengthcoStr);
            widthco = Double.parseDouble(widthcoStr);
            heightco = Double.parseDouble(heightcoStr);
            double volcuboid = lengthco*widthco*heightco;
            totalVol = volcuboid;
            double hdvalue = (totalVol*30)/spaceFor30Tabletscubic_meter;
            totalVol = Precision.round(totalVol,2);
            String hdvalueStr = Double.toString(hdvalue);
            double noftbrqed = Double.parseDouble(hdvalueStr);
            noOfTabletsRequired = (int)noftbrqed;
            struct = "Cuboid";
        }else if("volume-scenario3".equals(structure)){
            basefcStr = itemsArr[1];
            heightfcStr = itemsArr[2];
            lengthfcStr = itemsArr[3];
            basefc = Double.parseDouble(basefcStr);
            heightfc = Double.parseDouble(heightfcStr);
            lengthfc = Double.parseDouble(lengthfcStr);
            double volfumicover = 0.5*basefc*heightfc*lengthfc;
            totalVol = volfumicover;
            double hdvalue = (totalVol*30)/spaceFor30Tabletscubic_meter;
            totalVol = Precision.round(totalVol,2);
            String hdvalueStr = Double.toString(hdvalue);
            double noftbrqed = Double.parseDouble(hdvalueStr);
            noOfTabletsRequired = (int)noftbrqed;
            struct = "Fumicover";
        }else if(("volume-scenario4".equals(structure)) || ("volume-scenario5".equals(structure))){
            diameterclStr = itemsArr[1];
            heightclStr = itemsArr[2];
            heightcnStr = itemsArr[3];
            diametercl = Double.parseDouble(diameterclStr);
            heightcl = Double.parseDouble(heightclStr);
            heightcn = Double.parseDouble(heightcnStr);
            double volcylinder = 3.14*(diametercl*diametercl/4)*heightcl;
            double volcone = 1.05*(diametercl*diametercl/4)*heightcn;
            totalVol = volcylinder + volcone;
            double hdvalue = (totalVol*30)/spaceFor30Tabletscubic_meter;
            totalVol = Precision.round(totalVol,2);
            String hdvalueStr = Double.toString(hdvalue);
            double noftbrqed = Double.parseDouble(hdvalueStr);
            noOfTabletsRequired = (int)noftbrqed;
            struct = "Silo";
        }else if("volume-scenario6".equals(structure)){
            diameterscStr = itemsArr[1];
            heightscStr = itemsArr[2];
            diametersc = Double.parseDouble(diameterscStr);
            heightsc = Double.parseDouble(heightscStr);
            double volsack = 3.14*(diametersc*diametersc/4)*heightsc;
            totalVol = volsack;
            double hdvalue = (totalVol*30)/spaceFor30Tabletscubic_meter;
            totalVol = Precision.round(totalVol,2);
            String hdvalueStr = Double.toString(hdvalue);
            double noftbrqed = Double.parseDouble(hdvalueStr);
            noOfTabletsRequired = (int)noftbrqed;
            struct = "Sack";
        }
        double remainingFlasks = 0.00;
        noOfTabletsRequired = Math.round(noOfTabletsRequired);
        if(noOfTabletsRequired > 7000){
            double hdval = Math.floor(noOfTabletsRequired/7000);
            String hdvalStr = Double.toString(hdval);
            double dbnoOfCases = Double.parseDouble(hdvalStr);
            noOfCases = (int)dbnoOfCases;
            remainingFlasks = noOfTabletsRequired - (noOfCases*7000);
            if(remainingFlasks >= 500){
                hdval = Math.floor(remainingFlasks/500);
                hdvalStr = Double.toString(hdval);
                double dbnoOfFlasks = Double.parseDouble(hdvalStr);
                noOfFlasks = (int)dbnoOfFlasks;
                noOfTablets = 0;
                if((noOfCases == 1) && (noOfFlasks == 1)){
                    outputStr = noOfCases + " case " + noOfFlasks + " flask.";
                }else if((noOfCases == 1) && (noOfFlasks > 1)){
                    outputStr = noOfCases + " case " + noOfFlasks + " flasks.";
                }else if((noOfCases > 1) && (noOfFlasks > 1)){
                    outputStr = noOfCases + " cases " + noOfFlasks + " flasks.";
                }else if((noOfCases > 1) && (noOfFlasks == 1)){
                    outputStr = noOfCases + " cases " + noOfFlasks + " flasks";
                }
            }else{
                noOfTablets = (int) remainingFlasks;
                noOfFlasks = 0;
                if((noOfCases == 1) && (noOfTablets == 1)){
                    outputStr = noOfCases + " case " + noOfTablets + " tablet.";
                }else if((noOfCases == 1) && (noOfTablets > 1)){
                    outputStr = noOfCases + " case " + noOfTablets + " tablets.";
                }else if((noOfCases > 1) && (noOfTablets > 1)){
                    outputStr = noOfCases + " cases " + noOfTablets + " tablets.";
                }else if((noOfCases > 1) && (noOfTablets == 1)){
                    outputStr = noOfCases + " cases " + noOfTablets + " tablets";
                }
            }
        }else{
            noOfTablets = noOfTabletsRequired;
            noOfCases = 0;
            noOfFlasks = 0;
            if(noOfTablets == 1){
                outputStr = noOfTablets + " tablet.";
            }else if(noOfTablets > 1){
                outputStr = noOfTablets + " tablets.";
            }
        }

        String totalVolStr = Double.toString(totalVol);
        String pesticidedose = "";
        if("volume-scenario1".equals(structure)){
            pesticidedose += "Structure: " + struct + "|";
            pesticidedose += "Height of top prisim (meter): " + heightpStr + "|";
            pesticidedose += "Height of cuboid (meter): " + heightcbStr + "|";
            pesticidedose += "Length of cuboid (meter): " + heightcbStr + "|";
            pesticidedose += "Width of cuboid (meter): " + widthcbStr + "|";
            pesticidedose += "Total volume (cubic meter): " + totalVolStr + "|";
            pesticidedose += "Pesticide dosage: " + outputStr + "|";
        }else if("volume-scenario2".equals(structure)){
            pesticidedose += "Structure: " + struct + "|";
            pesticidedose += "Height of cuboid (meter): " + heightcoStr + "|";
            pesticidedose += "Length of cuboid (meter): " + lengthcoStr + "|";
            pesticidedose += "Width of cuboid (meter): " + widthcoStr + "|";
            pesticidedose += "Total volume (cubic meter): " + totalVolStr + "|";
            pesticidedose += "Pesticide dosage: " + outputStr + "|";
        }else if("volume-scenario3".equals(structure)){
            pesticidedose += "Structure: " + struct + "|";
            pesticidedose += "Height of fumicover (meter): " + heightfcStr + "|";
            pesticidedose += "Length of fumicover (meter): " + lengthfcStr + "|";
            pesticidedose += "Base of fumicover (meter): " + basefcStr + "|";
            pesticidedose += "Total volume (cubic meter): " + totalVolStr + "|";
            pesticidedose += "Pesticide dosage: " + outputStr + "|";
        }else if(("volume-scenario4".equals(structure)) ||("volume-scenario5".equals(structure))){
            pesticidedose += "Structure: " + struct + "|";
            pesticidedose += "Diameter of cylinder (meter): " + diameterclStr + "|";
            pesticidedose += "Height of cone (meter): " + heightcnStr + "|";
            pesticidedose += "Height of cylinder (meter): " + heightclStr + "|";
            pesticidedose += "Total volume (cubic meter): " + totalVolStr + "|";
            pesticidedose += "Pesticide dosage: " + outputStr + "|";
        }else if("volume-scenario6".equals(structure)){
            pesticidedose += "Structure: " + struct + "|";
            pesticidedose += "Diameter of sack (meter): " + diameterscStr + "|";
            pesticidedose += "Height of sack (meter): " + heightscStr + "|";
            pesticidedose += "Total volume (cubic meter): " + totalVolStr + "|";
            pesticidedose += "Pesticide dosage: " + outputStr + "|";
        }

        if(!pesticidedose.isEmpty()) {
            String[] arrelems = UtilFuncs.barDelstringToArray(pesticidedose);
            String pciderecsStr = outerToJson(arrelems);
            pcidUSEList.add(pciderecsStr + "<br>");
            compstatus = "Computation successful";
        }else {
            compstatus = "Computation unsuccessful";
        }
        return compstatus;
    }

    private <T> Set<T> convertListToSet(List<T> list){
        // create an empty set
        Set<T> set = new HashSet<>();

        // Add each element of list into the set
        for (T t : list)
            set.add(t);

        // return the set
        return set;
    }

    private <T> List<T> convertSetToList(Set<T> set){
        // create an empty list
        List<T> list = new ArrayList<>();

        // push each element in the set into the list
        for (T t : set)
            list.add(t);

        // return the list
        return list;
    }

    public Set<String> getRandNOSSet(){
        return convertListToSet(randNOSList);
    }

    public Set<String> getRelhEWCSet(){
        return convertListToSet(relhEWCList);
    }

    public Set<String> getFansSELSet(){
        return convertListToSet(fansSELList);
    }

    public Set<String> getPcidUSESet(){
        return convertListToSet(pcidUSEList);
    }

    public boolean setRandNOSList(List<String> tempRandRecsLst){
        return randNOSList.addAll(tempRandRecsLst);
    }

    public boolean setRelhEWCList(List<String> tempRhemcRecsLst){
        return relhEWCList.addAll(tempRhemcRecsLst);
    }

    public boolean setFansSELList(List<String> tempFanselRecsLst){
        return fansSELList.addAll(tempFanselRecsLst);
    }

    public boolean setPcidUSEList(List<String> tempPciduseRecsLst){
        return pcidUSEList.addAll(tempPciduseRecsLst);
    }

    public boolean setAllRecsList(List<String> tempAllRecsLst){
        return allrecordsList.addAll(tempAllRecsLst);
    }

    public String randNOSjsonString(){
        Object[] strObjs = randNOSList.toArray();
        int strobjln = strObjs.length;
        String[] strarray = new String[strobjln];
        for(int i = 0; i < strobjln; i++){
            strarray[i] = (String)strObjs[i];
        }
        String allrecssStr = outerToJson(strarray);
        storageRandNOSRecs = allrecssStr;
        return allrecssStr;
    }

    public String relhEWCjsonString(){
        Object[] strObjs = relhEWCList.toArray();
        int strobjln = strObjs.length;
        String[] strarray = new String[strobjln];
        for(int i = 0; i < strobjln; i++){
            strarray[i] = (String)strObjs[i];
        }
        String allrecssStr = outerToJson(strarray);
        storageRelhEWCRecs = allrecssStr;
        return allrecssStr;
    }

    public String fansSELjsonString(){
        Object[] strObjs = fansSELList.toArray();
        int strobjln = strObjs.length;
        String[] strarray = new String[strobjln];
        for(int i = 0; i < strobjln; i++){
            strarray[i] = (String)strObjs[i];
        }
        String allrecssStr = outerToJson(strarray);
        storageFansSELRecs = allrecssStr;
        return allrecssStr;
    }

    public String pcidUSEjsonString(){
        Object[] strObjs = pcidUSEList.toArray();
        int strobjln = strObjs.length;
        String[] strarray = new String[strobjln];
        for(int i = 0; i < strobjln; i++){
            strarray[i] = (String)strObjs[i];
        }
        String allrecssStr = outerToJson(strarray);
        storagePcidUSERecs = allrecssStr;
        return allrecssStr;
    }

    public String allRecsjsonString(){
        Object[] strObjs = allrecordsList.toArray();
        int strobjln = strObjs.length;
        String[] strarray = new String[strobjln];
        for(int i = 0; i < strobjln; i++){
            strarray[i] = (String)strObjs[i];
        }
        String allrecssStr = outerToJson(strarray);
        storageAllRecords = allrecssStr;
        return allrecssStr;
    }

    public String outerToJson(String[] data) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i = 0; i < data.length; i++) {
            String str = data[i];
            if (i > 0)
                sb.append("<br>");
            sb.append(str);
        }
        sb.append("");
        return sb.toString();
    }
}
