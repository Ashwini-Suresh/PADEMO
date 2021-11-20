// IMyAidlInterface.aidl
package Common;

// Declare any non-default types here with import statements
import com.example.personalaccounthmitrial.ProfileData;

interface IMyAidlInterface {
    java.util.List<ProfileData> getAll();
       void addProfile(String pName,String avatar);
}