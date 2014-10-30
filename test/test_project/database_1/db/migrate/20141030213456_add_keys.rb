class AddKeys < ActiveRecord::Migration
  def change
    add_foreign_key "comments", "posts", name: "comments_commented_on_id_fk", column: "commented_on_id"
    add_foreign_key "comments", "users", name: "comments_commenter_id_fk", column: "commenter_id"
    add_foreign_key "images", "users", name: "images_user_id_fk"
    add_foreign_key "posts", "users", name: "posts_user_id_fk"
  end
end
