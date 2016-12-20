import java.util.*;

public class EqualsAndHashCode {

    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        set.add(new Person("person1", 21));
        set.add(new Person("person2", 21));
        set.add(new Person("person3", 21));
        set.add(new Person("person1", 21));

        Iterator<Person> it = set.iterator();
        while (it.hasNext()) 
            System.out.println(it.next());

    }
    /**
     * @desc Person�ࡣ
     */
    private static class Person {

        int age;
        String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /** 
         * @desc ��дhashCode 
         */  
        @Override
        public int hashCode(){  
            int nameHash =  name.toUpperCase().hashCode();
            return nameHash ^ age;
        }

        /** 
         * @desc ����equals���� 
         */  
        @Override
        public boolean equals(Object obj){
            //if obj is null, return false  
            if(obj == null){  
                return false;  
            }  
            //�����ͬһ�����󷵻�true 
            if(this == obj){  
                return true;  
            }  
            //�ж��Ƿ�����ͬһ������
            if(this.getClass() != obj.getClass()){  
                return false;  
            } 

            Person person = (Person)obj;  
            return name.equals(person.name) && age==person.age;  
        }

        @Override
        public String toString() {
            return this.name + " " + this.age;
        }
    }
    
}