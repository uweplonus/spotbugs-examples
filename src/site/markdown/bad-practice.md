# Bad Practice

* [BC: Equals method should not assume anything about the type of its argument](#BC:_Equals_method_should_not_assume_anything_about_the_type_of_its_argument)
* [BIT: Check for sign of bitwise operation](#BIT:_Check_for_sign_of_bitwise_operation)

## BC: Equals method should not assume anything about the type of its argument

Example for bug:

```
    public boolean equals(Object o) {
        Sample casted = (Sample)o;
        ...
    }
```

In this example a ClassCastException would be thrown because the object `o` is cast without check.
The [`equals(Object)`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-)
method should never throw any exception as this violates the contract.

Project example: [`EqualsAssume`](./xref/de/sw4j/examples/equals/EqualsAssume.html#L27)

## BIT: Check for sign of bitwise operation

Example for bug:

```
    private static final int flag = 0x40000000;
    public boolean bug(int value) {
        boolean flagSet = ((value & flag) > 0);
        ...
    }
```

In this example the check (`(value & flag) > 0`) will fail when `flag` is a negative value
(e.g. `0x80000000`). Then the result will be also negative.

Project example: [`CheckSign`](./xref/de/sw4j/examples/bitoperation/CheckSign.html#L27)

### See also
* BIT: Check for sign of bitwise operation
