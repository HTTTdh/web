class ChatBot {
    constructor() {
        this.messageInput = document.getElementById("messageInput")
        this.chatMessages = document.getElementById("chatMessages")
        this.chatForm = document.getElementById("chatForm")
        this.sendBtn = document.getElementById("sendBtn")
        this.typingIndicator = document.getElementById("typingIndicator")
        this.clearChatBtn = document.getElementById("clearChat")
        this.charCount = document.querySelector(".char-count")

        this.init()
    }

    init() {
        this.setupEventListeners()
        this.updateSendButton()
    }

    setupEventListeners() {
        // Form submission
        this.chatForm.addEventListener("submit", (e) => {
            e.preventDefault()
            this.sendMessage()
        })

        // Input events
        this.messageInput.addEventListener("input", () => {
            this.updateCharCount()
            this.updateSendButton()
        })

        this.messageInput.addEventListener("keypress", (e) => {
            if (e.key === "Enter" && !e.shiftKey) {
                e.preventDefault()
                this.sendMessage()
            }
        })

        // Clear chat
        this.clearChatBtn.addEventListener("click", () => {
            this.clearChat()
        })

        // Auto-resize input
        this.messageInput.addEventListener("input", this.autoResize.bind(this))
    }

    sendMessage() {
        const message = this.messageInput.value.trim()
        if (!message) return

        // Add user message
        this.addMessage(message, "user")

        // Clear input
        this.messageInput.value = ""
        this.updateCharCount()
        this.updateSendButton()
        this.autoResize()

        // Show typing indicator
        this.showTypingIndicator()

        // Gửi đến server
        fetch(`http://127.0.0.1:8000/chatbot?user_input=${encodeURIComponent(message)}`, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        })
            .then((res) => {
                console.log("Raw response:", res)
                if (!res.ok) throw new Error("Network response was not ok")
                return res.json()
            })
            .then((data) => {
                this.hideTypingIndicator()

                if (data.response) {
                    this.addMessage(data.response, "bot")
                } else if (data.error) {
                    this.addMessage("Lỗi server: " + data.error, "bot")
                } else {
                    this.addMessage("Phản hồi không xác định", "bot")
                }
            })

            .catch((error) => {
                this.hideTypingIndicator()
                this.addMessage("Xin lỗi, đã có lỗi xảy ra. Vui lòng thử lại sau.", "bot")
                console.error("Fetch error:", error)
            })
    }

    addMessage(text, sender) {
        const messageDiv = document.createElement("div")
        messageDiv.className = `message ${sender}-message`

        const currentTime = new Date().toLocaleTimeString("vi-VN", {
            hour: "2-digit",
            minute: "2-digit",
        })

        const avatarIcon = sender === "user" ? "fas fa-user" : "fas fa-robot"

        messageDiv.innerHTML = `
            <div class="message-avatar">
                <i class="${avatarIcon}"></i>
            </div>
            <div class="message-content">
                <div class="message-bubble">
                    <p>${this.escapeHtml(text)}</p>
                </div>
                <div class="message-time">
                    <span>${currentTime}</span>
                </div>
            </div>
        `

        this.chatMessages.appendChild(messageDiv)
        this.scrollToBottom()
    }

    showTypingIndicator() {
        this.typingIndicator.style.display = "block"
        this.scrollToBottom()
    }

    hideTypingIndicator() {
        this.typingIndicator.style.display = "none"
    }

    scrollToBottom() {
        setTimeout(() => {
            this.chatMessages.scrollTop = this.chatMessages.scrollHeight
        }, 100)
    }

    updateCharCount() {
        const count = this.messageInput.value.length
        this.charCount.textContent = `${count}/1000`

        if (count > 800) {
            this.charCount.style.color = "#f44336"
        } else if (count > 600) {
            this.charCount.style.color = "#ff9800"
        } else {
            this.charCount.style.color = "#999"
        }
    }

    updateSendButton() {
        const hasText = this.messageInput.value.trim().length > 0
        this.sendBtn.disabled = !hasText
    }

    autoResize() {
        this.messageInput.style.height = "auto"
        this.messageInput.style.height = Math.min(this.messageInput.scrollHeight, 100) + "px"
    }

    clearChat() {
        if (confirm("Bạn có chắc chắn muốn xóa toàn bộ cuộc trò chuyện?")) {
            // Keep only the welcome message
            const welcomeMessage = this.chatMessages.querySelector(".message")
            this.chatMessages.innerHTML = ""
            this.chatMessages.appendChild(welcomeMessage)
        }
    }

    escapeHtml(text) {
        const div = document.createElement("div")
        div.textContent = text
        return div.innerHTML
    }
}

// Initialize chatbot when DOM is loaded
document.addEventListener("DOMContentLoaded", () => {
    new ChatBot()
})
