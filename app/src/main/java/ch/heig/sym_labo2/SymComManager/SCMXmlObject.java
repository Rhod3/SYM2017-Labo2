package ch.heig.sym_labo2.SymComManager;

import ch.heig.sym_labo2.activities.SCMActivities;
import ch.heig.sym_labo2.utils.Person;
import ch.heig.sym_labo2.utils.PhoneType;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class SCMXmlObject extends SCMAsyncSendRequest {

    public SCMXmlObject(SCMActivities activity) {
        super(activity);
    }

    public void sendPerson(String name, String firstname, String url){
        Person person = new Person(name, firstname);
        String xml = createXML(person);
        Request request = buildPostRequest(xml, url);
        getClient().newCall(request).enqueue(responseCallback());
    }

    @Override
    public Request buildPostRequest(String payload, String url) {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/xml; charset=utf-8"),
                payload
        );
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("content-type", "application/xml")
                .build();
    }

    private String createXML(Person person) {
        final String gender = "Woman";
        final String phone  = "079 717 28 48";
        final PhoneType phoneType = PhoneType.HOME;
        String format =
                "<?xml version='1.0' encoding='UTF-8'?>" +
                        "<!DOCTYPE directory SYSTEM \"http://sym.iict.ch/directory.dtd\">" +
                        "<directory>" +
                        "   <person>" +
                        "       <name>%s</name>" +
                        "       <firstname>%s</firstname>" +
                        "       <gender>%s</gender>" +
                        "       <phone type='%s'>%s</phone>" +
                        "   </person>" +
                        "</directory>";
        return String.format(format, person.getName(), person.getFirstname(), gender, phoneType.toString(), phone);
    }
}
