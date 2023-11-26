import java.util.*;
class Person implements Comparable<Person> {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {return name;}
    public int getAge() {return age;}
    @Override
    public int compareTo(Person otherPerson) {return Integer.compare(this.age, otherPerson.age);}
    @Override
    public String toString() {return name + " (" + age + " years old)";}
} // The end of the Person class
class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getName().compareTo(person2.getName()); // Сортировка по имени
    }
} // The end of the NameComparator class
class PeopleDatabase implements Iterable<Person> {
    private List<Person> people = new ArrayList<>();
    public void addPerson(Person person) {people.add(person);}
    @Override
    public Iterator<Person> iterator() {return new PeopleIterator();}
    private class PeopleIterator implements Iterator<Person> {
        private int currentIndex = 0;
        @Override
        public boolean hasNext() {return currentIndex < people.size();}
        @Override
        public Person next() {
            if(hasNext()) {return people.get(currentIndex++);}
            throw new java.util.NoSuchElementException();
        }
    } // The end of the PeopleIterator class
    public List<Person> getPeople() {return people;}
    public void sort() {
        Collections.sort(people);
        System.out.println(people);
    } // Метод сортировки по возрасту
    public void nameSort() {
        TreeSet<Person> personTreeSet = new TreeSet<>(new NameComparator());
        for (int i = 0; i < people.size(); i++) {personTreeSet.add(people.get(i));}
        System.out.println(personTreeSet); // Сортированный по имени
    } // Метод сортировки по имени
} // The end of the PeopleDatabase class
class NewMain {
    public static void main(String[] args) {
        PeopleDatabase database = new PeopleDatabase();
        database.addPerson(new Person("Alice", 25));
        database.addPerson(new Person("Bob", 30));
        database.addPerson(new Person("Charlie", 20));
        database.addPerson(new Person("Яша", 8));
        // Используем итератор для перебора элементов
        System.out.println("People in the database: ");
        List<Person> people = database.getPeople();
        System.out.println(people); // Несортированный
        database.sort(); // Сортированный по возрасту
        database.nameSort(); // Сортированный по имени
    } // The end of the main method
} // The end of the NewMain class
