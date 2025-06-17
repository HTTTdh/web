function updatePost() {
        const content = document.getElementById('contentEditor').value;
        const title = document.getElementById('postTitle').value;
        const id = document.getElementById('postId').value;
        console.log(content + " " + title + " " + id);

        const response =  fetch(`http://localhost:8080/post/updatePost?id=${id}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title, content }),
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) {
                    alert("Update successful!");
                    window.location.href = `/blog-detail?title=${encodeURIComponent(title)}`;
                } else {
                    return response.text().then(text => {
                        alert("Failed: " + text);
                    });
                }
            })
            .catch(error => {
                alert("Request error: " + error.message);
            });
}

function deletePost() {
    const id = document.getElementById('postId').value;
    console.log(id)
    if (confirm('Bạn có chắc chắn muốn xóa bài viết này không?')) {
        const response =  fetch(`http://localhost:8080/post/deletePost?postId=${id}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) {
                    alert("Delete successful!");
                    window.location.href = `/home`;
                } else {
                    return response.text().then(text => {
                        alert("Failed: " + text);
                    });
                }
            })
            .catch(error => {
                alert("Request error: " + error.message);
            });
    }
}

function formatText(command) {
    document.execCommand(command, false, null);
    document.getElementById('contentEditor').focus();
}

// Insert list in editor
function insertList(type) {
    document.execCommand(type === 'ul' ? 'insertUnorderedList' : 'insertOrderedList', false, null);
    document.getElementById('contentEditor').focus();
}

// Insert link in editor
function insertLink() {
    const url = prompt('Enter the URL:');
    if (url) {
        document.execCommand('createLink', false, url);
    }
    document.getElementById('contentEditor').focus();
}

// Insert code block in editor
function insertCode() {
    const selection = window.getSelection();
    const range = selection.getRangeAt(0);
    const selectedText = range.toString();

    const codeBlock = document.createElement('pre');
    const code = document.createElement('code');
    code.textContent = selectedText || 'Your code here';
    codeBlock.appendChild(code);

    range.deleteContents();
    range.insertNode(codeBlock);

    document.getElementById('contentEditor').focus();
}
