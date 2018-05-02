package org.example.xtend.examples;

import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.example.xtend.examples.Person;

@SuppressWarnings("all")
public class XtendHelloWorld {
  public static void main(final String[] args) {
    Person _person = new Person("James", "Smith", 50);
    Person _person_1 = new Person("John", "Smith", 40);
    Person _person_2 = new Person("James", "Anderson", 40);
    Person _person_3 = new Person("John", "Anderson", 30);
    Person _person_4 = new Person("Paul", "Anderson", 30);
    final ArrayList<Person> personList = CollectionLiterals.<Person>newArrayList(_person, _person_1, _person_2, _person_3, _person_4);
    final Function1<Person, Boolean> _function = (Person it) -> {
      return Boolean.valueOf(it.getFirstname().startsWith("J"));
    };
    final Function1<Person, Integer> _function_1 = (Person it) -> {
      return Integer.valueOf(it.getAge());
    };
    final Function1<Person, String> _function_2 = (Person it) -> {
      String _firstname = it.getFirstname();
      String _plus = (_firstname + " ");
      String _surname = it.getSurname();
      String _plus_1 = (_plus + _surname);
      String _plus_2 = (_plus_1 + "(");
      int _age = it.getAge();
      String _plus_3 = (_plus_2 + Integer.valueOf(_age));
      return (_plus_3 + ")");
    };
    final String result = IterableExtensions.join(ListExtensions.<Person, String>map(IterableExtensions.<Person, Integer>sortBy(IterableExtensions.<Person>filter(personList, _function), _function_1), _function_2), "; ");
    InputOutput.<String>println(result);
    InputOutput.<String>println(Person.extra);
  }
}
