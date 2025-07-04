package SixthProgram;

abstract class Post {
    protected String username;
    protected String content;
    protected boolean hasPhoto;
    protected int likes;

    public Post (String username, String content, boolean hasPhoto, int likes) {
        this.username = username;
        this.content = content;
        this.hasPhoto = hasPhoto;
        this.likes = likes;
    }

    public abstract void print ();
    public abstract double popularity ();

    boolean comparePosts (Post post) {
        return popularity() > post.popularity();
    }
}


class FacebookPost extends Post {
    private int shares;
    public FacebookPost (String username, String content, boolean hasPhoto,int likes , int shares) {
        super(username, content, hasPhoto, likes);
        this.shares = shares;
    }

    @Override
    public void print () {
        System.out.println ("Username: " + username);
        System.out.println ("Content: " + content);
        System.out.print ("Has Photo: ");
        if (hasPhoto) {
            System.out.println("With photo");
        } else {
            System.out.println("Without photo");
        }
        System.out.println ("Likes: " + likes + " Shares " + shares);
        System.out.println ("Popularity: " + popularity());
    }

    @Override
    public double popularity () {
        double initial = likes * shares;
        if (hasPhoto) {
            initial *= 1.20;
        }
        return initial;
    }

}


class TwitterPost extends Post {
    private int retweets;
    private int replies;
    public TwitterPost (String username, String content, boolean hasPhoto, int likes, int retweets, int replies) {
        super(username, content, hasPhoto, likes);
        this.retweets = retweets;
        this.replies = replies;
    }


    @Override
    public void print () {
        System.out.println ("Username: " + username);
        System.out.println ("Content: " + content);
        System.out.print ("Has Photo: ");
        if (hasPhoto) {
            System.out.println("With photo");
        } else {
            System.out.println("Without photo");
        }
        System.out.println ("Likes: " + likes + " Retweets " + retweets + " Replies " + replies);
        System.out.println ("Popularity: " + popularity());
    }

    @Override
    public double popularity () {
        double initial = likes * retweets * replies;
        if (hasPhoto) {
            initial *= 1.10;
        }

        if (content.contains("#crypto")) {
            initial *= 1.20;
        }
        return initial;
    }

}


public class FacebookAndTwitterPost {

    public static double mostPopularPostWithPhoto (Post[] posts) {
        double max = -1;
        for (int i = 0; i < posts.length; i++) {
            if (posts[i].hasPhoto) {
                if (max < posts[i].popularity()) {
                    max = posts[i].popularity();
                }
            }
        }
        return max;
    }

    public static void main (String []args) {
        Post[] posts = new Post[5];
        //TESTING
        posts[0] = new FacebookPost("alice", "Enjoying the sunny day!", true, 100, 20);
        posts[1] = new FacebookPost("bob", "No filter!", false, 150, 10);
        posts[2] = new TwitterPost("charlie", "Just got into #crypto", true, 120, 15, 5);
        posts[3] = new TwitterPost("dana", "Tech news update", false, 80, 20, 3);
        posts[4] = new TwitterPost("eve", "Look at this chart! #crypto", true, 100, 10, 10);

        System.out.println("---- All Posts ----");
        for (Post post : posts) {
            post.print();
            System.out.println();
        }

        System.out.println("---- Most Popular Post With Photo ----");
        double maxPopularity = mostPopularPostWithPhoto(posts);
        System.out.println("Max popularity (with photo): " + maxPopularity);

        System.out.println("---- Comparing Post 2 and Post 3 ----");
        boolean isPost2MorePopular = posts[2].comparePosts(posts[3]);
        System.out.println("Post 2 is more popular than Post 3: " + isPost2MorePopular);
    }
}
