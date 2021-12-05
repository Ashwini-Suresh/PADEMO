// IPersonalAccount.aidl
package common;
import common.IPersonalAccountListener;
// Declare any non-default types here with import statements
import com.training.personalaccountservice.ProfileData;

interface IPersonalAccount {
  java.util.List<ProfileData> getAllProfile();
  void addProfile(String profileName, String profileAvatar);
  void changeActiveProfile(int pId);
  java.util.List<String> getAvailableAvatarList();
  void updateProfileName(String newName);
  void updateProfileAvatar(String newAvatar);
  void registerCallback(IPersonalAccountListener callback);
  ProfileData activeProfileData();
  void deleteProfile();
  long profileCount();
  }