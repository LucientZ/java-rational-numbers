
This is a list of functions that actually have to be developed:

```java
// Constructors
Rational(): void // good
Rational(int): void // good
Rational(int, int): void // good 
Rational(Rational): void // good

// Accessors
numerator(): int // good
denominator(): int // good

// Simple operations
opposite(): Rational // check max/min integer limit behavior
reciprocal(): Rational 

// Arithmetic operations
times(Rational): Rational
dividedBy(Rational): Rational
plus(Rational): Rational
minus(Rational): Rational
raisedToThePowerOf(int): Rational // Check this one and see if it can be optimized

// Property boolean methods
isZero(): boolean
isOne(): boolean
isMinusOne(): boolean

// Comparison methods
lessThan(Rational): boolean
lessThan(Number): boolean
greaterThan(Rational): boolean
greaterThan(Number): boolean
equals(Object): boolean

// Pretty printing method :D
toString(): String

// Helper methods
simplify(): void // good
gcd(int, int): int
lcm(int, int): int

// Integer
intValue(): int
longValue(): int
floatValue(): int
doubleValue(): int
```