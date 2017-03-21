package sample.spring.boot.io

import org.junit.Assert
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime

/**
 * Created by yamashiro-r on 2017/03/21.
 */

class FilesTest {

    @Test
    fun testCreateDirectory() {
        val path = Paths.get("tmp/test/hoge")
        Files.createDirectories(path)
        Assert.assertTrue("exists directory:$path", Files.exists(path))
    }

    @Test
    fun testCreateDirectoryAndWriteFile() {
        val resource = Paths.get("tmp/img/java_friends.png")
        val bytes = Files.readAllBytes(resource)
        val path = Paths.get("tmp/test/", LocalDateTime.now().nano.toString())
        val output = Paths.get(path.toString(), resource.fileName.toString())
        Files.createDirectories(path)
        Files.write(output, bytes)
        Assert.assertTrue("exists file:$output", Files.exists(output))
    }

}
