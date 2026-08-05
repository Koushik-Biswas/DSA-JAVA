/**
 * @param {Array} arr
 * @param {number} depth
 * @return {Array}
 */
var flat = function(arr, depth) {
    const result = [];

    function dfs(current, d) {
        for (const item of current) {
            if (Array.isArray(item) && d < depth) {
                dfs(item, d + 1);
            } else {
                result.push(item);
            }
        }
    }

    dfs(arr, 0);
    return result;
};