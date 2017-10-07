# Bad Practice

* [CNT: Rough value of known constant found](#CNT:_Rough_value_of_known_constant_found)
* [BC: Equals method should not assume anything about the type of its argument](#BC:_Equals_method_should_not_assume_anything_about_the_type_of_its_argument)
* [BIT: Check for sign of bitwise operation](#BIT:_Check_for_sign_of_bitwise_operation)
* [CN: Class implements Cloneable but does not define or use clone method](#CN:_Class_implements_Cloneable_but_does_not_define_or_use_clone_method)
* [CN: clone method does not call super.clone()](#CN:_clone_method_does_not_call_super.clone)
* [CN: Class defines clone() but doesn't implement Cloneable](#CN:_Class_defines_clone_but_doesnt_implement_Cloneable)
* [Co: Abstract class defines covariant compareTo() method](#Co:_Abstract_class_defines_covariant_compareTo_method)

## CNT: Rough value of known constant found

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#cnt-rough-value-of-known-constant-found-cnt-rough-constant-value)

Example for bug:

```
        circumference = diameter * 3.141;
```

In this example the circumference of a circle is calculated. The value ̣π is is inserted as an approximate value. It is
better to use a constant (e.g. [`Math.PI`](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#PI)).

Project example: [`KnownConstant`](./xref/de/sw4j/examples/numbers/KnownConstant.html#L25)


## BC: Equals method should not assume anything about the type of its argument

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#bc-equals-method-should-not-assume-anything-about-the-type-of-its-argument-bc-equals-method-should-work-for-all-objects)

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

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#bit-check-for-sign-of-bitwise-operation-bit-signed-check)

Example for bug:

```
    private static final int FLAG = 0x40000000;
    public boolean bug(int value) {
        boolean flagSet = ((value & FLAG) > 0);
        ...
    }
```

In this example the check (`(value & FLAG) > 0`) will fail when `FLAG` is a negative value
(e.g. `0x80000000`). Then the result will be also negative.

Project example: [`CheckSign`](./xref/de/sw4j/examples/bitoperation/CheckSign.html#L27)

### See also
* BIT: Check for sign of bitwise operation

## CN: Class implements Cloneable but does not define or use clone method

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#cn-class-implements-cloneable-but-does-not-define-or-use-clone-method-cn-idiom)

Example for bug:

```
public class NoClone implements Cloneable {
    // no clone()
}
```

In this example the created class does not override the
[`clone()`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#clone--) method.

Project example: [`NoClone`](./xref/de/sw4j/examples/clone/NoClone.html#L22)

## CN: clone method does not call super.clone()

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#cn-clone-method-does-not-call-super-clone-cn-idiom-no-super-call)

Example for bug:

```
    public Object clone() throws CloneNotSupportedException {
        // no call to super.clone();
    }
```

In this example the created class does call the `clone()` method of the super class. Every class
that implements the `Cloneable` must call the method `super.clone()` to ensure that the `clone()`
returns the correct class.

Project example: [`NoSuperClone`](./xref/de/sw4j/examples/clone/NoSuperClone.html#L26)

## CN: Class defines clone() but doesn't implement Cloneable

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#cn-class-defines-clone-but-doesn-t-implement-cloneable-cn-implements-clone-but-not-cloneable)

Example for bug:

```
public class NoClone {
    public Object clone() throws CloneNotSupportedException {
        ...
    }
}
```

In this example the created class overrides the method `clone()` but does not implement the
interface `Cloneable`. This is at least uncommon and not expected.

Project example: [`NoCloneable`](./xref/de/sw4j/examples/clone/NoCloneable.html#L26)

## Co: Abstract class defines covariant compareTo() method

[SpotBugs bug description](https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#co-abstract-class-defines-covariant-compareto-method-co-abstract-self)

Example for bug:

```
public abstract class Covariant implements Comparable<Covariant> {
    @Override
    public abstract int compareTo(Covariant o);
}
```

In this example the class `Covariant` implements the interface `Comparable` and implements the
method `compareTo` with itself as argument. This is problematic as subclasses cannot override this
method with another argument.

Project example: [`Covariant`](./xref/de/sw4j/examples/compare/Covariant.html#L22)
