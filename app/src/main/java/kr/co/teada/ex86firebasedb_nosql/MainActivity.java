package kr.co.teada.ex86firebasedb_nosql;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);

    }//end of onCreate

    public void clickSend(View view) {

        //EditText에 있는 글씨 얻어오기
        String text=et.getText().toString();
        //et.setText(""); //다음 입력을 위해 et에 있는 글씨 없애기 ""

        //Firebase 실시간 DB 저장

        //1. Firebase 실시간 데이터베이스 관리 객체 얻어오기
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        //2. 저장시킬 노드 참조객체 가져오기
        DatabaseReference rootRef=firebaseDatabase.getReference(); //최상위 노드

        //각 노드에 값 대입하기

//        //1) 별도의 키(key:식별자) 없이 값(value)저장하기 : 위에 et 에서 글씨 가져와! 한 text: et 에서 입력받은 글씨 찍어라
//        rootRef.setValue(text);
//
//        //저장된 값 읽어오기
//        //별도의 읽어오기 버튼 없이 .. [실시간 데이터베이스]인 만큼
//        //데이터의 변경이 발생하면 이에 반응하는 리스너를 통해서
//        //실시간으로 데이터 읽어옴 !!! push 고 뭐고 다 필요없어 대신, 앱이 켜져있을 때만 가능
//
//        //값이 변경되는 노드참조객체에 리스너를 추가
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //값이 변경될 때 마다 실행되는 메소드
//
//                //파라미터로 전달된 DataSnapshot 객체를 통해 데이터들을 가져올 수 있음
//                //String data=(String) dataSnapshot.getValue();
//                //형변환이 귀찮으면 getValue() 메소드의 매개변수로 자료형.class 지정
//                String data=dataSnapshot.getValue(String.class); //이게 형변환보다 속도가 더 빨라 : .class 는 그 안에 있는 메소드, 참조변수에 대한 정보 갖고있어
//                tv.setText(data);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                //값 변경 중 에러 발생시 실행되는 메소드
//
//            }
//        });


//        //2) 위의 1)번 방법은 값이 [갱신] 되는거야. 누적하여 추가하고 싶다면 ..//////////////////////////////////////////////////////////////////////////////////
//
//        DatabaseReference childRef=rootRef.push(); //push() : DB에 노드 하나 추가
//        childRef.setValue(text);
//
//        //읽어오기
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //최상위 노드에 바로 값이 있는 것이 아니라..
//                //자식 노드들이 여러개 있으므로 String 밸류가 아님
//                //String text=dataSnapshot.getValue(String.class);      : dataSnapshot 은 큰 덩어리!
//
//                StringBuffer buffer=new StringBuffer();    //반복문 하는 이유: 데이터가 한 개가 아니니까 여러개니까    : snapshot 은 child
//                //각 자식 노드들에 foreach 문을 이용해서 접근하여 값 읽어오기
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    String text=snapshot.getValue(String.class);
//                    buffer.append(text+"\n");
//                }
//                tv.setText(buffer.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//        //3) 자식 노드들의 식별 키값을 부여하면서 데이터 관리하기 ex)채팅처럼 키,밸류가 보낸사람, 메세지 처럼 : 지금은 밸류는 나오는데 키가 지맘대로 나오잖아
//
//        //rootRef 아래에 'data' 라는 이름의 자식 노드 하나 추가
//        DatabaseReference dataRef=rootRef.child("data"); //최상위 노드 아래에 'data'라는 이름의 키값에 접근(없으면 만들고, 있으면 그냥 참조)
//        //dataRef.setValue("aaa"); //set Value 값이 갱신..
//        dataRef.push().setValue(text);
//
//        //읽어오려면 리스너 달아야 해 : rootRef 가 아니라 dataRef 에 리스너 추가
//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //String s=dataSnapshot.getValue(String.class);
//                //값들이 여러개 이므로 .. foreach 문 사용
//                StringBuffer buffer=new StringBuffer();
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    String str=snapshot.getValue(String.class);
//                    buffer.append(str+"\n");
//                }
//                tv.setText(buffer.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        //4) 하나의 노드에 밸류가 여러개 되도록(가지 뻗어나가기) : ex)회원명단
//        DatabaseReference memberRef=rootRef.child("member");
//        DatabaseReference itemRef =memberRef.push(); //"member" 노드 아래에 임의의 식별자를 가진 자식 노드가 생김 push : 추가
//        itemRef.child("name").setValue(text);
//        itemRef.child("age").setValue(20); //원래는 여기도 사용사로부터 입력(EditText) 받아야 해 : 기본 자료형 다 들어가
//
//
//        //'member'라는 이름의 노드(node)에 리스너 추가하기 : itemRef 한테 다는거 아니야~ 주의! 이건 자식 하나에만 해당되서 member 에 달아야해
//        memberRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                StringBuffer buffer=new StringBuffer(); //한 개가 아니라 여러개니까 버퍼
//
//                //push()에 의한 자식노드들 하나씩 접근하기
//                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    //각 자식 노드들 마다 'name', 'age'라는 자식 노드가 또 있으므로
//                    for (DataSnapshot snap : snapshot.getChildren()){ //중첩반복
//                        buffer.append(snap.getKey()+" : "+ snap.getValue().toString()+"\n"); //String 이랑 int 가 같이 있으니까 String.class 말고 toString 으로 value 한방에 받아서 스트링으로 찍어주는걸로
//
//                    }
//                    buffer.append("\n");
//                }
//                tv.setText(buffer.toString());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


            //5) 나만의 클래스 객체[VO:Value Object]를 만들어서 한번에 멤버값들 저장하기 : 쌤이라면 이 방식으로 짠대!
            //멤버 변수명으로 자동으로 자식 노드들이 생성됨!!
            //이름, 나이, 주소 또 미친듯이 반복문 안쓰고 하는 방법
            String name=et.getText().toString();
            int age=25; //나이랑 주소도 EditText 로 받아서 써야해
            String address="SEOUL";

            //저장할 값들을 하나의 객체로 생성하기: 별도의 PersonVO 클래스 만들어서
            PersonVO person=new PersonVO(name, age, address);

            //'persons'라는 이름의 자식노드 참조객체 생성 or 참조
            DatabaseReference personsRef=rootRef.child("persons");
            personsRef.push().setValue(person);

            //'persons'노드의 밸류가 변경되는 것을 듣는 리스너 추가!!
            personsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //persons 노드 아래에 자식 노드들이 여러개 이므로..
                    StringBuffer buffer=new StringBuffer();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        PersonVO person=snapshot.getValue(PersonVO.class);
                        String name=person.name;
                        int age=person.age;
                        String address=person.address;

                        buffer.append(name+","+age+","+address+"\n");
                    }

                    tv.setText( buffer.toString() );

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        et.setText("");


    }//end of clickSend




}//end of MainActivity
