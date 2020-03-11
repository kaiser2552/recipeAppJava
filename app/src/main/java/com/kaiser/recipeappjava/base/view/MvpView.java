package com.kaiser.recipeappjava.base.view;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

    void updateApiCallCount(boolean start);

    void updateProgressDialog(boolean isShowProgressDialog);

    void onUpdateStaffTokenSuccess(String entryRole, String userId, String name, String agentRank);

    void showErrorMessageDialog(String errorTitle, String errorMessage, Boolean isBackLogin);
}
