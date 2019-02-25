package kr.co.teada.ex86firebasedb_nosql;

import java.io.Serializable;

public class PersonVO{


     String name;
     int age;
     String address;

    //alt+insert ; Constructor 3개 다 받아
    public PersonVO(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }//end of  Constructor

    //firebase DB 에서 getValue()를 하려면..
    //빈 파라미터 생성자가 있어야 함..!! (필수-!!!)
    //alt+insert 해서 빈 파라미터 (아무것도 체크 안하고 위의 클래스만 선택)
    public PersonVO() {
    }


    //getter setter
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }



}//end of PersonVO
