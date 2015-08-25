;(function($) {
    $(function() {
        console.log('start Select2!!');

        var testSelect2 = $('.user');

        $.ajax({
            url: '/api/user/find',
            type: 'GET',
            dataType: 'json',
            data: {
                name: "yamashiro"
            },
        })
        .done(function(data) {
            console.log("success", data);
            var obj = data[0];
            var option = $('<option>').val(obj.id).html(obj.text);
            testSelect2.append(option);

            testSelect2.select2({
                placeholder: 'User Select2',
                minimumInputLength: 1,
                minimumResultsForSearch: 1,
                maximumInputLength: 6,
                ajax: {
                    url: "/api/user/find",
                    dataType: 'json',
                    delay: 250,
                    data: function (params) {
                        return {
                            name: params.term,
                            // page: params.page
                        };
                    },
                    processResults: function (data, page) {
                        return {
                            results: data
                        };
                    },
                    cache: true
                },
            });
        })
        .fail(function() {
            console.log("error");
        })
        .always(function() {
            console.log("complete");
        });

    });
})(jQuery);
