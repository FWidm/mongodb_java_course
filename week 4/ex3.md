The blog has been enhanced so that it can also display the top 10 most recent posts by tag. There are hyperlinks from the post tags to the page that displays the 10 most recent blog entries for that tag. (run the blog and it will be obvious)

Your assignment is to make the following blog pages fast:

- The blog home page
- The page that displays blog posts by tag (http://localhost:8082/tag/whatever)
- The page that displays a blog entry by permalink (http://localhost:8082/post/permalink)
By fast, we mean that indexes should be in place to satisfy these queries such that we only need to scan the number of documents we are going to return.

To figure out what queries you need to optimize, you can read the code and see what it does to display those pages. Isolate those queries and use explain to explore.

When you believe you have solved the problem correctly, test your solution in MongoProc. When you see confirmation that your solution is correct, turn it in.

You will see a message below about the number of times you have submitted a solution through MongoProc. You should not submit until testing in MongoProc confirms that your solution is correct.

Tip: Be sure to go to settings in mongoProc, and point mongod1 to your mongod (probably localhost:27017), and web1 to your web url (probably localhost:8082)

Answer:

- The blog home page
  - Blog homepage uses sort desc on posts, thus we create indexes for dates descending: `> db.posts.createIndex({date:-1})`
- The page that displays blog posts by tag (http://localhost:8082/tag/whatever)
  - `> db.posts.createIndex({"tags":1},{"tags.date":-1})`
- The page that displays a blog entry by permalink (http://localhost:8082/post/permalink)
  - This page searches via the permalink, we add `> db.posts.createIndex({permalink:1})`
