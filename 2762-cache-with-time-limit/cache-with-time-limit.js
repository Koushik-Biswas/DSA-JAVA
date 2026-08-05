class TimeLimitedCache {
    constructor() {
        this.cache = new Map();
    }

    /**
     * @param {number} key
     * @param {number} value
     * @param {number} duration
     * @return {boolean}
     */
    set(key, value, duration) {
        const exists = this.cache.has(key);

        if (exists) {
            clearTimeout(this.cache.get(key).timeoutId);
        }

        const timeoutId = setTimeout(() => {
            this.cache.delete(key);
        }, duration);

        this.cache.set(key, { value, timeoutId });

        return exists;
    }

    /**
     * @param {number} key
     * @return {number}
     */
    get(key) {
        if (!this.cache.has(key)) {
            return -1;
        }

        return this.cache.get(key).value;
    }

    /**
     * @return {number}
     */
    count() {
        return this.cache.size;
    }
}