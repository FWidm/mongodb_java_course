package course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.javadoc.Doc;
import org.bson.BsonArray;
import org.bson.BsonDateTime;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogPostDAO {
    MongoCollection<Document> postsCollection;

    public BlogPostDAO(final MongoDatabase blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public Document findByPermalink(String permalink) {

        // XXX HW 3.2,  Work Here
        Document filter = new Document("permalink",permalink);
        Document post = postsCollection.find(filter).first();



        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<Document> findByDateDescending(int limit) {

        // XXX HW 3.2,  Work Here
        // Return a list of DBObjects, each one a post from the posts collection
//        List<Document> posts = postsCollection.find()
//                .sort(new Document("date ", -1))
//                .limit(limit).into(new ArrayList<Document>());
        List<Document> posts = postsCollection.find().sort(new Document("date",-1)).limit(limit).into(new ArrayList<Document>());

        System.out.println("Value from the DB" + posts);

        return posts;
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();


        // XXX HW 3.2, Work Here
        // Remember that a valid post has the following keys:
        // author, body, permalink, tags, comments, date, title
        //
        // A few hints:
        // - Don't forget to create an empty list of comments
        // - for the value of the date key, today's datetime is fine.
        // - tags are already in list form that implements suitable interface.
        // - we created the permalink for you above.

        // Build the post object and insert it
        Document post = new Document("author",username);
        post.append("permalink",permalink);
        post.append("body",body);
        post.append("comments",new BsonArray());
        post.append("tags",new BsonArray(tags));
        post.append("date",new Date());
        post.append("title",title);

        postsCollection.insertOne(post);

        return permalink;
    }




    // White space to protect the innocent








    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {

        // XXX HW 3.3, Work Here
        // Hints:
        // - email is optional and may come in NULL. Check for that.
        // - best solution uses an update command to the database and a suitable
        //   operator to append the comment on to any existing list of comments
        Document comment = new Document().append("author",name).append("body",body);
        if(email!=null && email.length()>0){
            comment.append("email",email);
        }
        System.out.println("comment="+comment);

        System.out.println("adding to post="+postsCollection.find(new Document("permalink",permalink)).first());
//        System.out.println(collection.updateOne(new Document("_id",doc.get("_id")),
//                new Document("$pull",new Document("scores",lowestHw))));
        System.out.println(postsCollection.updateOne(new Document("permalink",permalink), new Document("$push",new Document("comments",comment))));
    }
}
