package object;

import interaction.BallVisitor;

public abstract class GameObject {

    public abstract <T> T collideDetection(BallVisitor<T> ballVisitor);

    public abstract <T> T collideReaction(BallVisitor<T> ballVisitor);

}
