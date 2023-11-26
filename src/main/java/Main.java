import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Формируем группу 1
        StudyGroupIterator group1 = new StudyGroupIterator();
        group1.add(new Student("Серж",33,2007));
        group1.add(new Student("Ириша", 32, 2008));
        group1.add(new Student("Kristof", 40, 1990));
        // Формируем группу 2
        StudyGroupIterator group2 = new StudyGroupIterator();
        group2.add(new Student("Василиса",13,2006));
        group2.add(new Student("Квентин", 36, 2008));
        group2.add(new Student("Peter", 40, 2990));
        // Формируем группу 3
        StudyGroupIterator group3 = new StudyGroupIterator();
        group3.add(new Student("Ада",13,2006));
        group3.add(new Student("Иосифъ", 136, 1008));
        group3.add(new Student("Аделаида", 12, 2014));
        // Создаем поток 1
        GroupIterator stream1 = new GroupIterator();
        stream1.addGroupList(group1.getGroupStudentList());
        stream1.addGroupList(group2.getGroupStudentList());
        // Создаем поток 2
        GroupIterator stream2 = new GroupIterator();
        stream2.addGroupList(group3.getGroupStudentList());
        // Печать списков групп потока
        stream1.printStream(stream1);
        // Создаем компаратор потоков
        StreamComparator comparator = new StreamComparator();
        comparator.addStream(stream1.getGroupsList());
        comparator.addStream(stream2.getGroupsList());
        // Печатаем попоточно
        System.out.println(comparator.getStreamsList().get(0));
        System.out.println("-".repeat(19));
        // Сравним потоки 1 и 2
        comparator.compare(0,1);
        // Сортируем
        StreamService sort = new StreamService();
        sort.setSortedStreamsList(comparator.getStreamsList());
        if (comparator.compare(0,1) == 1) {
            System.out.println("Сортировано: ");
            System.out.println(comparator.getStreamsList().get(1));
            System.out.println(comparator.getStreamsList().get(0));
        }
        else if (comparator.compare(0,1) == -1) {
            System.out.println("Сортировано: ");
            System.out.println(comparator.getStreamsList().get(0));
            System.out.println(comparator.getStreamsList().get(1));
        }
        else {
            System.out.println("Сортировано: ");
            System.out.println(comparator.getStreamsList().get(0));
            System.out.println(comparator.getStreamsList().get(1));
        }
//        // Печать данных студента
//        System.out.println(stream2.getGroup(0).get(2).toString());
//        // Печать списка группы
//        stream2.printGroup(0);
//        System.out.println(group1.find("Kristof",40,1990));
//        group1.setRemoveIndex(group1.find("Kristof",40,1990));
//        group1.remove();
    } // The end of the main method
} // The end of the Main class
class Student {
    private String name;
    private int age;
    private int startStudyYear;
    public Student(String name, int age, int startStudyYear) {
        this.name = name;
        this.age = age;
        this.startStudyYear = startStudyYear;
    }
    public String getName() {return name;}
    public int getAge() {return age;}
    public int getStartStudyYear() {return startStudyYear;}
    @Override
    public String toString() {return name + " (" + age + " years old, in " + startStudyYear + " start of study)";}
} // The end of the Student class
class StudyGroupIterator implements Iterator<Student> {
    private List<Student> students = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    public boolean hasNext() {return currentIndex < students.size();} // Метод проверки, а есть ли следующий

    @Override
    public Student next() {
        if(hasNext()) {return students.get(currentIndex++);}
        throw new java.util.NoSuchElementException();
    } // Метод выдачи следующего

    @Override
    public void remove() {students.remove(currentIndex);}

    public void add(Student student) {students.add(student);}
    public void print(int index) {
        if(index < students.size()) {System.out.println(students.get(index).toString());}
        else {System.out.println("тоби п#зда");}
    }
    public int find(String name, int age, int startStudyYear) {
        int index = -1;
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getName().equals(name) &&
                    students.get(i).getAge() == age &&
                            students.get(i).getStartStudyYear() == startStudyYear) {index = i;}
        }
        return index;
    } // Метод поиска студента по указанным параметрам, возвращает индекс
    public void setRemoveIndex(int removeIndex) {
        this.currentIndex = removeIndex;
    }
    public List<Student> getGroupStudentList() {return students;}
} // The end of the StudyGroupIterator class
class Group {
    private String nameGroup;
    private int studyStart;
    public Group(String nameGroup, int studyStart) {
        this.nameGroup = nameGroup;
        this.studyStart = studyStart;
    }
    public String getNameStream() {return nameGroup;}
    public int studyStart() {return studyStart;}
    @Override
    public String toString() {return nameGroup + " (" + studyStart + " start of study)";}
} // The end of the Stream class
class GroupIterator implements Iterator<List<Student>> {
    private List<List<Student>> groups = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    public boolean hasNext() {return currentIndex < groups.size();} // Метод проверки, а есть ли следующий

    @Override
    public List<Student> next() {
        if(hasNext()) {return groups.get(currentIndex++);}
        throw new java.util.NoSuchElementException();
    } // Метод выдачи следующего
    public void addGroupList(List<Student> groupStudentList) {groups.add(groupStudentList);}
    public List<Student> getGroup(int index) {return groups.get(index);}
    public void printGroup(int index) {
        System.out.println("Список студентов группы " + index + ": ");
        System.out.println(groups.get(index));
    } // The end of the printGroup method
    public int size() {return groups.size();}
    public void printStream(GroupIterator stream) {
        System.out.println("ПОТОК");
        for (int i = 0; i < groups.size(); i++) {stream.printGroup(i);}
        System.out.println("-".repeat(19));
    } // The end of the printStream method
    public List<List<Student>> getGroupsList() {return groups;}
} // The end of the StreamIterator class
class StreamComparator implements Iterator<List<List<Student>>> {
    private List<List<List<Student>>> streams = new ArrayList<>();
    private int currentIndex = 0;
    @Override
    public boolean hasNext() {return currentIndex < streams.size();} // Метод проверки, а есть ли следующий

    @Override
    public List<List<Student>> next() {
        if(hasNext()) {return streams.get(currentIndex++);}
        throw new java.util.NoSuchElementException();
    } // Метод выдачи следующего
    public void addStream(List<List<Student>> stream) {streams.add(stream);}
    public List<List<List<Student>>> getStreamsList() {return streams;}
    public int compare(int streamNumOne, int streamNumTwo) {
        if (streams.get(streamNumOne).size() < streams.get(streamNumTwo).size()) {
//            System.out.println("В потоке №" + streamNumOne + " меньше групп, чем в потоке №" + streamNumTwo);
            return 1;
        } else if (streams.get(streamNumOne).size() > streams.get(streamNumTwo).size()) {
//            System.out.println("В потоке №" + streamNumOne + " больше групп, чем в потоке №" + streamNumTwo);
            return -1;
        } else {
//            System.out.println("В потоке №" + streamNumOne + " и в потоке №" + streamNumTwo + " одинаковое число групп");
            return 0;
        }
    }
} // The end of the StreamComparator class
class StreamService implements Iterator<List<List<Student>>> {
    private List<List<List<Student>>> sortingStreams = new ArrayList<>();
    private List<List<List<Student>>> sortedStreams = new ArrayList<>();
    private int currentIndex = 0;
    @Override
    public boolean hasNext() {return currentIndex < sortingStreams.size();} // Метод проверки, а есть ли следующий

    @Override
    public List<List<Student>> next() {
        if(hasNext()) {return sortingStreams.get(currentIndex++);}
        throw new java.util.NoSuchElementException();
    } // Метод выдачи следующего
    public void setSortedStreamsList(List<List<List<Student>>> list) {sortingStreams = list;}
    public List<List<List<Student>>> getSortedStreamsList() {return sortingStreams;}
    public void DirectSort() {
        for(int i = 0;  i < sortingStreams.size(); i++) {
            int minPos = i;
            for(int j = i + 1; j < sortingStreams.size(); j++) {
                if(sortingStreams.get(j).size() < sortingStreams.get(minPos).size()) minPos = j;
            }
        }
    }
} // The end of the StreamService class