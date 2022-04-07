package todo.models;

import java.time.LocalDate;
import java.util.Set;

public class Todo {

    private Integer todoId;
    private String text;
    private Integer userId;
    private Boolean isPublic;
    private LocalDate createDate;

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (todoId != null ? !todoId.equals(todo.todoId) : todo.todoId != null) return false;
        if (text != null ? !text.equals(todo.text) : todo.text != null) return false;
        if (userId != null ? !userId.equals(todo.userId) : todo.userId != null) return false;
        if (isPublic != null ? !isPublic.equals(todo.isPublic) : todo.isPublic != null) return false;
        return createDate != null ? createDate.equals(todo.createDate) : todo.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = todoId != null ? todoId.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (isPublic != null ? isPublic.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
