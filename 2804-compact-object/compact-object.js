/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {
    // Primitive value
    if (obj === null || typeof obj !== "object") {
        return obj;
    }

    // Array
    if (Array.isArray(obj)) {
        const result = [];

        for (const item of obj) {
            const value = compactObject(item);
            if (Boolean(value)) {
                result.push(value);
            }
        }

        return result;
    }

    // Object
    const result = {};

    for (const key in obj) {
        const value = compactObject(obj[key]);
        if (Boolean(value)) {
            result[key] = value;
        }
    }

    return result;
};