# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20141030213456) do

  create_table "comments", :force => true do |t|
    t.text     "content"
    t.integer  "commenter_id",                                                    :null => false
    t.integer  "commented_on_id", :limit => 8,                                    :null => false
    t.datetime "created_at",                   :default => '1970-01-01 00:00:00', :null => false
  end

  add_index "comments", ["commented_on_id"], :name => "comments_commented_on_id_fk"
  add_index "comments", ["commenter_id"], :name => "comments_commenter_id_fk"

  create_table "images", :force => true do |t|
    t.integer "user_id"
  end

  add_index "images", ["user_id"], :name => "images_user_id_fk"

  create_table "posts", :force => true do |t|
    t.string   "title"
    t.date     "posted_at_millis"
    t.integer  "user_id"
    t.datetime "updated_at"
  end

  add_index "posts", ["user_id"], :name => "posts_user_id_fk"

  create_table "users", :force => true do |t|
    t.string   "handle",                                                         :null => false
    t.integer  "created_at_millis", :limit => 8
    t.integer  "num_posts",                                                      :null => false
    t.date     "some_date"
    t.datetime "some_datetime"
    t.text     "bio"
    t.binary   "some_binary"
    t.float    "some_float"
    t.decimal  "some_decimal",                   :precision => 20, :scale => 10
    t.boolean  "some_boolean"
  end

  add_foreign_key "comments", "posts", name: "comments_commented_on_id_fk", column: "commented_on_id"
  add_foreign_key "comments", "users", name: "comments_commenter_id_fk", column: "commenter_id"

  add_foreign_key "images", "users", name: "images_user_id_fk"

  add_foreign_key "posts", "users", name: "posts_user_id_fk"

end
