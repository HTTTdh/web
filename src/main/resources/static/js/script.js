// // Toggle mobile menu
// function toggleMobileMenu() {
//     const nav = document.querySelector('.nav');
//     nav.classList.toggle('active');
// }
//
// // Logout function
// function logout() {
//     alert('Đăng xuất thành công!');
//     window.location.href = 'index.html';
// }
//
// // Tab switching in profile page
// function showTab(tabId) {
//     // Hide all tabs
//     const tabs = document.querySelectorAll('.tab-content');
//     tabs.forEach(tab => {
//         tab.classList.remove('active');
//     });
//
//     // Deactivate all tab buttons
//     const tabBtns = document.querySelectorAll('.tab-btn');
//     tabBtns.forEach(btn => {
//         btn.classList.remove('active');
//     });
//
//     // Show selected tab
//     document.getElementById(tabId).classList.add('active');
//
//     // Activate selected tab button
//     document.querySelector(`.tab-btn[onclick="showTab('${tabId}')"]`).classList.add('active');
// }
//
// // Preview image for blog post
// function previewImage(input) {
//     if (input.files && input.files[0]) {
//         const reader = new FileReader();
//
//         reader.onload = function(e) {
//             const preview = document.getElementById('imagePreview');
//             preview.innerHTML = `<img src="${e.target.result}" alt="Preview">`;
//             preview.classList.remove('hidden');
//         }
//
//         reader.readAsDataURL(input.files[0]);
//     }
// }
//
// // Preview avatar
// function previewAvatar(input) {
//     if (input.files && input.files[0]) {
//         const reader = new FileReader();
//
//         reader.onload = function(e) {
//             document.getElementById('avatarPreview').src = e.target.result;
//         }
//
//         reader.readAsDataURL(input.files[0]);
//     }
// }
//
// // Add tag in blog editor
// function addTag() {
//     const tagInput = document.getElementById('tagInput');
//     const tagsContainer = document.getElementById('tagsContainer');
//
//     if (tagInput.value.trim() !== '') {
//         const tagItem = document.createElement('div');
//         tagItem.className = 'tag-item';
//         tagItem.innerHTML = `
//             ${tagInput.value.trim()}
//             <button class="tag-remove" onclick="removeTag(this)">
//                 <i class="fas fa-times"></i>
//             </button>
//         `;
//
//         tagsContainer.appendChild(tagItem);
//         tagInput.value = '';
//     }
// }
//
// // Handle tag input with Enter key
// function handleTagInput(event) {
//     if (event.key === 'Enter') {
//         event.preventDefault();
//         addTag();
//     }
// }
//
// // Remove tag
// function removeTag(button) {
//     button.parentElement.remove();
// }
//
// // Format text in editor
// function formatText(command) {
//     document.execCommand(command, false, null);
//     document.getElementById('contentEditor').focus();
// }
//
// // Insert list in editor
// function insertList(type) {
//     document.execCommand(type === 'ul' ? 'insertUnorderedList' : 'insertOrderedList', false, null);
//     document.getElementById('contentEditor').focus();
// }
//
// // Insert link in editor
// function insertLink() {
//     const url = prompt('Enter the URL:');
//     if (url) {
//         document.execCommand('createLink', false, url);
//     }
//     document.getElementById('contentEditor').focus();
// }
//
// // Insert code block in editor
// function insertCode() {
//     const selection = window.getSelection();
//     const range = selection.getRangeAt(0);
//     const selectedText = range.toString();
//
//     const codeBlock = document.createElement('pre');
//     const code = document.createElement('code');
//     code.textContent = selectedText || 'Your code here';
//     codeBlock.appendChild(code);
//
//     range.deleteContents();
//     range.insertNode(codeBlock);
//
//     document.getElementById('contentEditor').focus();
// }
//
// // Save post
// function savePost() {
//     alert('Bài viết đã được lưu thành công!');
//     window.location.href = 'blog-detail.html';
// }
//
// // Update post
// function updatePost() {
//     alert('Bài viết đã được cập nhật thành công!');
//     window.location.href = 'blog-detail.html';
// }
//
// // Preview post
// function previewPost() {
//     alert('Đang xem trước bài viết...');
// }
//
// // Delete blog
// function deleteBlog() {
//     if (confirm('Bạn có chắc chắn muốn xóa bài viết này không?')) {
//         alert('Bài viết đã được xóa thành công!');
//         window.location.href = 'blogs.html';
//     }
// }
//
// // Save profile
// function saveProfile() {
//     alert('Thông tin hồ sơ đã được cập nhật thành công!');
//     window.location.href = 'profile.html';
// }
//
// // Like blog
// function likeBlog() {
//     alert('Đã thích bài viết!');
// }
//
// // Share blog
// function shareBlog() {
//     alert('Đã sao chép liên kết bài viết vào clipboard!');
// }
//
// // Search posts
// function searchPosts() {
//     const searchInput = document.getElementById('searchInput');
//     alert(`Đang tìm kiếm: ${searchInput.value}`);
// }
//
// // Filter posts by category
// document.addEventListener('DOMContentLoaded', function() {
//     const filterButtons = document.querySelectorAll('.filter-btn');
//     if (filterButtons.length > 0) {
//         filterButtons.forEach(button => {
//             button.addEventListener('click', function() {
//                 // Remove active class from all buttons
//                 filterButtons.forEach(btn => btn.classList.remove('active'));
//
//                 // Add active class to clicked button
//                 this.classList.add('active');
//
//                 const category = this.getAttribute('data-category');
//                 const posts = document.querySelectorAll('.post-card');
//
//                 posts.forEach(post => {
//                     if (category === 'all' || post.getAttribute('data-category') === category) {
//                         post.style.display = 'block';
//                     } else {
//                         post.style.display = 'none';
//                     }
//                 });
//             });
//         });
//     }
//
//     // Character counter for textarea
//     const textareas = document.querySelectorAll('textarea[maxlength]');
//     textareas.forEach(textarea => {
//         textarea.addEventListener('input', function() {
//             const maxLength = this.getAttribute('maxlength');
//             const currentLength = this.value.length;
//             const counterId = this.id + 'Count';
//             const counter = document.getElementById(counterId);
//
//             if (counter) {
//                 counter.textContent = currentLength;
//             }
//         });
//     });
//
//     // Content editor character count
//     const contentEditor = document.getElementById('contentEditor');
//     if (contentEditor) {
//         contentEditor.addEventListener('input', function() {
//             const text = this.innerText;
//             const charCount = document.getElementById('charCount');
//             const readTime = document.getElementById('readTime');
//
//             if (charCount) {
//                 charCount.textContent = `${text.length} ký tự`;
//             }
//
//             if (readTime) {
//                 const words = text.trim().split(/\s+/).length;
//                 const minutes = Math.ceil(words / 200);
//                 readTime.textContent = `Ước tính ${minutes} phút đọc`;
//             }
//         });
//     }
// });