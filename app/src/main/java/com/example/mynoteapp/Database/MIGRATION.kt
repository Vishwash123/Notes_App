import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION = object : Migration(1, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Create a new table with the updated schema
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS notes_table_new (
                noteD_id INTEGER PRIMARY KEY AUTOINCREMENT,
                noteD_title TEXT,
                noteD_content TEXT,
                noteD_date TEXT,
                noteD_selected INTEGER DEFAULT 0
            )
        """.trimIndent())

        // Copy data from the old table to the new table
        db.execSQL("""
            INSERT INTO notes_table_new (noteD_id, noteD_title, noteD_content, noteD_date, noteD_selected)
            SELECT noteD_id, noteD_title, noteD_content, noteD_date,
                   CASE WHEN noteD_selected = 1 THEN 1 ELSE 0 END
            FROM notes_table
        """.trimIndent())

        // Drop the old table
        db.execSQL("DROP TABLE notes_table")

        // Rename the new table to the old table name
        db.execSQL("ALTER TABLE notes_table_new RENAME TO notes_table")
    }
}
