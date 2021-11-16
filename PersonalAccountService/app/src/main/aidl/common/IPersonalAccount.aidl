// IPersonalAccount.aidl
package common;

// Declare any non-default types here with import statements
import com.training.personalaccountservice.ProfileData;

interface IPersonalAccount {
  ProfileData getProfile();
  /*String updateProfileName(String name);

  boolean canDelete();
  boolean canCreate();*/
  }