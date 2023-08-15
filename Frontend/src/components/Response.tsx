import React from 'react'

interface props {
    response: object
}

const Response = ({ response }: props) => {
    // const formattedJson = JSON.stringify(response, null, 2);
    let formattedJson = JSON.stringify(response, null, 2).replace(/\\n|\\/g, '');
    formattedJson = formattedJson.slice(1, -1);

    const handleCopyClick = () => {
        navigator.clipboard.writeText(formattedJson);
        alert('JSON copied to clipboard!');
    };

    const handleCopyJsonString = () => { 
        navigator.clipboard.writeText(JSON.stringify(response, null, 2));
        alert('JSON String copied to clipboard!');
    }

    return (
        <div className=''>
            { JSON.stringify(response) !== '{}' && (
                <div className='w-[60%] flex mx-auto mb-20 pb-20 mt-10'>
                    <div className='mr-4 font-semibold text-xl mt-8'>AI</div>
                    <div className='bg-gray-200 font-medium py-4 px-4 drop-shadow-xl my-4 rounded-xl whitespace-pre-line w-full'>
                        <div className='p-4 border rounded-lg bg-gray-900 text-white'>
                            <div className='flex justify-between items-center mb-2'>
                                <h2 className='text-lg font-semibold'>JSON Data</h2>

                                <div>
                                    <button
                                        className='px-2 py-1 mx-1 bg-blue-600 hover:bg-blue-700 rounded text-white'
                                        onClick={handleCopyJsonString}
                                    >
                                        Copy JSON String
                                    </button>
                                    <button
                                        className='px-2 py-1 mx-1 bg-blue-600 hover:bg-blue-700 rounded text-white'
                                        onClick={handleCopyClick}
                                    >
                                        Copy JSON
                                    </button>
                                </div>

                            </div>
                            <pre className='overflow-x-auto whitespace-pre-line'>
                                <code>{formattedJson}</code>
                            </pre>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Response;
