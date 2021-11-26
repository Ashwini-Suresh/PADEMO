// IPersonalAccount.aidl
package common;
import  com.example.personalaccounthmi.ProfileData;

// Declare any non-default types here with import statements

interface IPersonalAccount {
     java.util.List<ProfileData> getAllProfile();
      void addProfile(String profileName, String profileAvatar);
      void changeActiveProfile(int pId);
     /* void editProfileName(String profileName);
      void editAvatar(String profileAvatar);
      void deleteProfile();*/
     java.util.List<String> getAvailableAvatar();
}