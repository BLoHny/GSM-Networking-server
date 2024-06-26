package team.themoment.gsmNetworking.domain.comment.domain

import io.micrometer.core.lang.Nullable
import team.themoment.gsmNetworking.common.domain.BaseIdTimestampEntity
import team.themoment.gsmNetworking.domain.board.domain.Board
import team.themoment.gsmNetworking.domain.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "comment")
class Comment(
    @Column(name = "comment", columnDefinition = "TEXT", nullable = false, length = 300)
    val comment: String,

    @ManyToOne
    @JoinColumn(name = "board_id")
    val board: Board,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    val parentComment: Comment?,

    // 해당 댓글의 대댓글 List
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "repliedComment", cascade = [CascadeType.ALL], orphanRemoval = true)
    val replyComment: MutableList<Comment> = ArrayList(),

    // 해당 댓글이 대댓글을 작성한 댓글
    @ManyToOne
    @JoinColumn(name = "replied_comment_id")
    var repliedComment: Comment? = null,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: User
): BaseIdTimestampEntity() {
    fun addRepliedComment(repliedComment: Comment) {
        this.repliedComment = repliedComment;
    }
}
