# java-rational-numbers

This repo contains a rational number class for CSCE 315. The following is documentation on the process of its creation and 

# Functional Requirements

Here are some basic requirements that a rational number would need:

1. Addition
2. Subtraction
3. Multiplication
4. Division
5. Reciprocal
6. Comparison

## Client's Functional Requirements

1. Construct a new `Rational`
    - Default: 0
    - From integer
    - From numerator and denominator
    - From `Rational`
2. Get the components of a `Rational`
    1. numerator
    2. denominator
3. Compute the opposite of a `Rational` (Not entirely sure what this entails)
4. Compute the reciprocal of a `Rational`
5. Addition
6. Subtraction
7. Multiplication
8. Division
9. Exponentiation of an integer power
10. Comparison
    - Equality
    - Greater than
    - Less than
    - Is zero
    - Is one
    - Is negative one
11. Pretty Printing

## Refined Functional Requirements

1. Construct a new `Rational`
    - Default: 0
        - Input: `None`
        - Output/Side-Effects: returns `void`. `numerator` is 0 and denominator is set to `1`
        - Invalid Input Handling: There is no user input, so no invalid input handling
    - From integer
        - Input: `int numerator`
        - Output/Side-Effects: denominator is set to 1
        - Invalid Input Handling: Any integer will suffice, so there is no invalid user input handling.
    - From numerator and denominator
        - Input: `int numerator`, `int denominator`
        - Output/Side-Effects: 
        - Invalid Input Handling: If the denominator is zero, throw an error
    - From `Rational`
        - Input: `Rational copiedRational`
        - Output/Side-Effects: `this.numerator = copiedRational.numerator` `this.denominator = copiedRational.denominator`
        - Invalid Input Handling: This rational number will have already been created, meaning it is likely okay to just read it. Perhaps checking if the denominator is 0 and throwing an exception if so
2. Get the components of a `Rational`
    - numerator
        - Input: `None`
        - Output/Side-Effects: returns `this.numerator` 
        - Invalid Input Handling: There is no input
    - denominator
        - Input: `None`
        - Output/Side-Effects: returns `this.denominator` 
        - Invalid Input Handling: There is no input
3. Compute the opposite of a `Rational` (Negative)
    - Input: `None`
    - Output/Side-Effects: Creates and returns a new `Rational` with the numerator being `this.numerator * (-1)` and denominator being the same
    - Invalid Input Handling: 
4. Compute the reciprocal of a `Rational`
    - Input: `None`
    - Output/Side-Effects:  Creates and returns a new `Rational` with `this.numerator` and `this.denominator` switched
    - Invalid Input Handling: If `this.numerator == 0`, throws exception
5. Addition
    - Input: `Rational otherRational`
    - Output/Side-Effects: creates and returns a new `Rational` that is the result of adding `otherRational` to `this`
    - Invalid Input Handling: none
6. Subtraction
    - Input: `Rational otherRational`
    - Output/Side-Effects: creates and returns a new `Rational` that is the result of subtracting `otherRational` from `this`
    - Invalid Input Handling: none
7. Multiplication
    - Input: `Rational otherRational`
    - Output/Side-Effects: creates and returns a new `Rational` that is the result of multiplying `otherRational` with `this`
    - Invalid Input Handling: none
8. Division
    - Input: `Rational otherRational`
    - Output/Side-Effects:  creates and returns a new `Rational` that is the result of dividing `this` by `otherRational`
    - Invalid Input Handling: if `otherRational.numerator == 0`, throw error
9. Exponentiation of an integer power
    - Input: `int power`
    - Output/Side-Effects: creates and returns a new `Rational` that is the result of raising `this` to the power of `power`
    - Invalid Input Handling: Throws exception if `power` is both negative and this.numerator == 0
10. Comparison
    - Equality
        - Input: `Object n`
        - Output/Side-Effects: Returns true if the given object is both a number and numerically the same as this number 
        - Invalid Input Handling: none
    - Greater than
        - Input: `Object n`
        - Output/Side-Effects: Returns true if the given object is both a number and numerically less than or equal as this number 
        - Invalid Input Handling: none
    - Less than
        - Input: `Object n`
        - Output/Side-Effects: Returns true if the given object is both a number and greater than than or equal as this number
        - Invalid Input Handling: none
    - Is zero
        - Input: none
        - Output/Side-Effects: Returns `this.numerator == 0`
        - Invalid Input Handling: none
    - Is one
        - Input: none
        - Output/Side-Effects: Returns `this.numerator == this.denominator`
        - Invalid Input Handling: none 
    - Is negative one
        - Input: none
        - Output/Side-Effects: Returns `true` if either `this.numerator < 0` or `this.denominator < 0`, but not both 
        - Invalid Input Handling: none
11. Pretty Printing
    - Input: `None`
    - Output/Side-effects: Returns a string representing this number. For example: "2/3"
    - Invalid Input Handling: there is no input


