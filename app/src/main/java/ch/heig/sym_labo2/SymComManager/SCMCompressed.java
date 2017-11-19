package ch.heig.sym_labo2.SymComManager;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import ch.heig.sym_labo2.activities.SCMActivities;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SCMCompressed extends SCMJsonObject {

    public SCMCompressed(SCMActivities activity) {
        super(activity);
    }

    public Callback responseCallback() {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String tmp = "";
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        tmp += responseHeaders.name(i) + ": " + responseHeaders.value(i) + "\n";
                    }
                    String resBody = inflate(responseBody.bytes());
                    tmp += resBody;
                    final String res = tmp;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().setResponseText(res);
                        }
                    });
                } catch (DataFormatException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void sendRequest(String filename, String url) throws Exception {
        String json = super.loadJSONFromAsset(filename);
        Request request = buildPostRequest(json, url);
        getClient().newCall(request).enqueue(responseCallback());
    }

    @Override
    public Request buildPostRequest(String payload, String url) {
        RequestBody requestBody = null;
        try {
            byte[] compressed = deflate(payload);
            int tmp = compressed.length;
            requestBody = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    deflate(payload)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("X-Network", "CSD")
                .addHeader("X-Content-Encoding", "deflate")
                .build();
    }

    private byte[] deflate(String toDeflate) throws UnsupportedEncodingException {
        byte[] input = toDeflate.getBytes("UTF-8");
        // Compress the bytes
        byte[] output = new byte[toDeflate.length()];
        Deflater compresser = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
        compresser.setInput(input);
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();

        byte[] res = new byte[compressedDataLength];
        System.arraycopy(output, 0, res, 0, compressedDataLength);
        return res;
    }

    private String inflate(byte[] toInflate) throws DataFormatException {
        // Decompress the bytes
        int compressedDataLength = toInflate.length;
        Inflater decompresser = new Inflater(true);
        decompresser.setInput(toInflate, 0, compressedDataLength);
        byte[] result = new byte[compressedDataLength];
        int resultLength = decompresser.inflate(result);
        decompresser.end();

        String outputString = null;
        try {
            outputString = new String(result, 0, resultLength, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return outputString;
    }
}
