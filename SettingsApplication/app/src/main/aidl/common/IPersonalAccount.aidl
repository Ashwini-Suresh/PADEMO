
// IPersonalAccount.aidl
package common;

// Declare any non-default types here with import statements
import com.example.settingsapplication.ProfileData;

interface IPersonalAccount {
  java.util.List<ProfileData> getAllProfile();
  void addProfile(String profileName, String profileAvatar);

  }