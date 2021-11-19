// IMyAidlInterface.aidl
package Common;

// Declare any non-default types here with import statements
import com.example.personalaccounthmi.ProfileData;
interface IMyAidlInterface {
    java.util.List<ProfileData> getAll();

}