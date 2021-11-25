package sqltutorial.evmsmobile.data.api;

import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import sqltutorial.evmsmobile.ui.login.LoginViewModel;

//import static sqltutorial.evmsmobile.ui.login.LoginFragment.CURRENT_LOGGEDIN_USER_EMAIL;

// Data will be passed as a string
public class AsyncDataConnectTask extends AsyncTask<RestApiCall, Integer, String> {

    ViewModel viewModel;

    public AsyncDataConnectTask(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected String doInBackground(RestApiCall... restApiCalls) {
        restApiCalls[0].sendRequest();

        return restApiCalls[0].response;
    }

    @Override
    protected void onPostExecute(String s) {

        if (viewModel instanceof LoginViewModel) {
            LoginViewModel loginViewModel = (LoginViewModel) viewModel;
            loginViewModel.setResult(s);
        }
        
        /*
        else if (viewModel instanceof RegistrationViewModel) {
            System.out.println(s);
            RegistrationViewModel registrationViewModel = (RegistrationViewModel) viewModel;
            registrationViewModel.setSignupResult(s);
        } else if (viewModel instanceof AddNoteViewModel) {
            AddNoteViewModel addNoteViewModel = (AddNoteViewModel) viewModel;
            Note note = null;
            try {
                JSONObject jo = new JSONObject(s);
                note = new Note(jo.getInt("id"), jo.getString("title"), jo.getString("type"), jo.getString("description"), jo.getString("createdBy"), jo.getString("lastUpdatedBy"));
                System.out.println(note);
                addNoteViewModel.setAddNoteResult(note);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (viewModel instanceof SharedNoteViewModel) {
            System.out.println(s);
            SharedNoteViewModel sharedNoteViewModel = (SharedNoteViewModel) viewModel;

            try {
                JSONArray ja = new JSONArray(s);
                List<String> emailList = new ArrayList<>();
                for (int i = 0; i < ja.length(); i++) {
                    emailList.add(ja.getString(i));
                }
                System.out.println(emailList);
                sharedNoteViewModel.setSharedEmailNoteResult(emailList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                sharedNoteViewModel.setSharedNoteResult(s);
            }
        } else if (viewModel instanceof DisplayNoteViewModel) {
            System.out.println(s);
            DisplayNoteViewModel displayNoteViewModel = (DisplayNoteViewModel) viewModel;
            ArrayList<Note> noteList = new ArrayList<Note>();
            try {
                JSONArray ja = new JSONArray(s);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    Note note = new Note(jo.getInt("id"), jo.getString("title"), jo.getString("type"), jo.getString("description"), jo.getString("createdBy"), jo.getString("lastUpdatedBy"));
                    JSONArray jan = jo.getJSONArray("noteUsers");
                    if (jan != null && jan.length() > 0) {
                        ArrayList<NoteUser> noteUserList = new ArrayList<>();
                        for (int j = 0; j < jan.length(); j++) {
                            JSONObject jou = jan.getJSONObject(j);
                            JSONObject joui = jou.getJSONObject("noteUserId");
                            String sharedEmail = joui.getString("email");
                            noteUserList.add(new NoteUser(new NoteUserId(joui.getInt("noteId"), sharedEmail)));
                            if (CURRENT_LOGGEDIN_USER_EMAIL.equals(sharedEmail)) {
                                noteList.add(note);
                            }
                        }
                    }
                }
                System.out.println(noteList);

            } catch (Exception e) {
                e.printStackTrace();
            }
            displayNoteViewModel.setNoteList(noteList);
        }
        */
    }
}
