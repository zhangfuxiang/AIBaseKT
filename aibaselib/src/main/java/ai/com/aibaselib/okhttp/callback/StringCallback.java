package ai.com.aibaselib.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by song on 4/8/2016.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }
}
