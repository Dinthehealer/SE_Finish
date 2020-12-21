package Model;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class DatabaseConnection {
    MongoClient mongoClient = new MongoClient();
    MongoDatabase db= mongoClient.getDatabase("local");
    MongoCollection<Document> collection=  db.getCollection("Account");
    Document doc;
    public boolean findRow(String id,String pass){
        try (MongoCursor<Document> cur = collection.find().iterator()) {
            while (cur.hasNext()) {
                var doc = cur.next();
                var acc = new ArrayList<>(doc.values());
                String Upass=decrypt(pass);boolean check=Upass.equals(acc.get(2));
                System.out.println(Upass+" "+pass+" "+check+"\n"+acc.get(2));
                if(id.equals(acc.get(1).toString())&&check){
                    mongoClient.close();
                    return true;
                }
            }
        }
        return false;
    }
    public String decrypt(String pass){
        String upass = "";int temp;int i=0;
        for(i=pass.length();i>0;i--){
            upass+=pass.charAt(i-1);
        }
        temp=Integer.parseInt(upass);temp+=123456;
        upass=String.valueOf(temp);
        upass=tranpassword(upass);
        return upass;
    }
    public String tranpassword(String pass){
        String Upasstran = "",Upasstran2="";int a=Integer.parseInt(pass);
        char[] Key1={'z','a','b','c','d','e','f','g','h','i'};
        char[] Key2={'X','C','D','E','F','G','H','I','J','K'};
        for(int i=0;i<6;i++){
            for(int j=0;j<10;j++){
                if((a%10)==j){
                    a/=10;
                    Upasstran=(Key1[j]+Upasstran);
                    Upasstran2=(Key2[j]+Upasstran2);break;
                }
            }
//            System.out.println("Tranpssword Upass :"+Upasstran+"  "+Upasstran2);
        }
       Upasstran=Upasstran+"@"+Upasstran2;
        System.out.println("TrantoString "+Upasstran);
        return  Upasstran;
    }

    public boolean findID(String id){
        System.out.println("tranfermodel id:"+id);
        try (MongoCursor<Document> cur = collection.find().iterator()) {
            while (cur.hasNext()) {
                var doc = cur.next();
                var acc = new ArrayList<>(doc.values());
                if(id.equals(acc.get(1).toString())){
                    mongoClient.close();
                    return true;
                }
            }
        }
        return false;
    }
    public String[] findRow(String id) {
        String [] Data=new String[5];
        collection=  db.getCollection("AccountData");
        try (MongoCursor<Document> cur = collection.find().iterator()) {
            while (cur.hasNext()) {
                var doc = cur.next();
                var acc = new ArrayList<>(doc.values());
                if(id.equals(acc.get(1).toString())){
                    Data[1]=acc.get(1).toString();
                    Data[2]=acc.get(2).toString();
                    Data[3]=acc.get(3).toString();
                    Data[4]=acc.get(4).toString();
                }
            }
        }
        return Data;
    }



//Insert MongoDB
//    public void dbInsert(){
//        collection=  db.getCollection("AccountData");
//        doc=new Document();
//        doc.put("UId",1994518492);
//        doc.put("Uname","Supawich");
//        doc.put("ULastname","Unsan");
//        doc.put("Balance",0);
//        collection.insertOne(doc);
//    }
        public void dbUpdate(int id,double money){
        collection=  db.getCollection("AccountData");
        Bson filter= eq("UId", id);
        Bson update;
           update= set("Balance", money);
        collection.updateOne(filter,update);
    }
    public void dbUpdate(int id,String pass){
        collection=  db.getCollection("Account");
        Bson filter= eq("UId", id);
        Bson update;
        update= set("UPass", pass);
        collection.updateOne(filter,update);
    }



}
