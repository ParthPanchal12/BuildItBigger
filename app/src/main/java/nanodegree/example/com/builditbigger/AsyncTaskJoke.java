package nanodegree.example.com.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.example.parth_panchal.myapplication.backend.myApi.MyApi;
import com.example.parth_panchal.myapplication.backend.myApi.model.MyBean;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by parth panchal on 19-09-2016.
 */

class AsyncTaskJoke extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private iAsynTaskJokeCallback delegate;

    public AsyncTaskJoke(iAsynTaskJokeCallback delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(Void... params) {


        String data=null;

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8888/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            final MyApi.Joke joke = myApiService.joke();
            final MyBean execute = joke.execute();
            data = execute.getData();

        } catch (IOException e) {
            Log.e(AsyncTaskJoke.class.getSimpleName(), e.getMessage(), e);
        }

        return data;

    }


    @Override
    protected void onPostExecute(String result) {
        delegate.setJoke(result);
    }

}